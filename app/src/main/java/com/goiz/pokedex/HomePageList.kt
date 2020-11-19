package com.goiz.pokedex

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.RecyclerView
import com.goiz.pokedex.adapters.PokemonAdapter
import com.goiz.pokedex.api.PokemonApi
import com.goiz.pokedex.model.Pokemon
import com.goiz.pokedex.model.PokemonResponse
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import pl.droidsonroids.gif.GifImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class HomePageList : AppCompatActivity(), PokemonAdapter.CellClickListener {
    private val pokeApi = PokeApiClient()

    private val title by lazy { findViewById<TextView>(R.id.title)}
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    lateinit var adapter: RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_list)
        val username = intent.getStringExtra("Username")

        val page = 0
        doAsync {
            val newTitle = "Oi, $username"
            title.text = newTitle
            val pokemonChainList = pokeApi.getEvolutionChainList(page*10, 10)
            val pokemonList = mutableListOf<Pokemon>()
            pokemonChainList.results.forEach {
                val pokemonChain = pokeApi.getEvolutionChain(it.id)
                val id = pokemonChain.chain.species.id
                val pokemonDetails = pokeApi.getPokemon(id)
                pokemonList.add(Pokemon(id, pokemonDetails.name, pokemonDetails.types[0].type.name, it.id))
            }
            uiThread {
                findViewById<GifImageView>(R.id.gif_loading).visibility = View.GONE
                adapter = PokemonAdapter(pokemonList, it, it)
                recyclerView.adapter = adapter
                title.text = resources.getString(R.string.pokemons)
            }
        }
    }

    fun handleGoToAboutActivity(view: View){
        view.animate().apply {
            duration = 300
            translationYBy(-10f)
        }.withEndAction {
            view.animate().apply {
                duration = 300
                translationYBy(10f)
            }.withEndAction {
                startActivity(Intent(this, About::class.java))
            }
        }.start()
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCellClickListener(data: Pokemon) {
        val intent = Intent(this, PokemonActivity::class.java).apply {
            putExtra("PokemonId", data.id.toString())
            putExtra("PokemonChainId", data.chain.toString())
            putExtra("PokemonType", data.type)
        }
        startActivity(intent)
    }
}
