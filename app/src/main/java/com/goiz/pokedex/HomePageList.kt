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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_list)

        val page = 0

        doAsync {
            val pokeApi = PokeApiClient()
            val pokemons = pokeApi.getPokemonList(page*10, 10)
            val pokemonList = mutableListOf<Pokemon>()
            pokemons.results.forEach {
                val pokemonDetails = pokeApi.getPokemon(it.id)
                pokemonList.add(Pokemon(it.id, it.name, pokemonDetails.types[0].type.name))
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
        val intent = Intent(this, PokemonActivity::class.java)
        intent.putExtra("PokemonId", data.id.toString())
        startActivity(intent)
    }
}
