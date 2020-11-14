package com.goiz.pokedex

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.goiz.pokedex.adapters.PokemonAdapter
import com.goiz.pokedex.model.Pokemon
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import pl.droidsonroids.gif.GifImageView

class HomePageList : AppCompatActivity(), PokemonAdapter.CellClickListener {
    private val pokeApi = PokeApiClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_list)

        val page = 0

        doAsync {
            val pokemonChainList = pokeApi.getEvolutionChainList(page*10, 10)
            val pokemonList = mutableListOf<Pokemon>()
            pokemonChainList.results.forEach {
                val pokemonChain = pokeApi.getEvolutionChain(it.id)
                val id = pokemonChain.chain.species.id
                val pokemonDetails = pokeApi.getPokemon(id)
                pokemonList.add(Pokemon(id, pokemonDetails.name, pokemonDetails.types[0].type.name, it.id))
            }
            uiThread {
                val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                findViewById<GifImageView>(R.id.gif_loading).visibility = View.GONE
                recyclerView.adapter = PokemonAdapter(pokemonList, it, it)
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCellClickListener(data: Pokemon) {
        val intent = Intent(this, PokemonActivity::class.java).apply {
            putExtra("PokemonId", data.id.toString())
            putExtra("PokemonChainId", data.chain.toString())
        }
        startActivity(intent)
    }
}
