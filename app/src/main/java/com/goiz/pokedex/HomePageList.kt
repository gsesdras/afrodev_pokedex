package com.goiz.pokedex

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.goiz.pokedex.adapters.PokemonAdapter
import com.goiz.pokedex.model.pokemonList
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import pl.droidsonroids.gif.GifImageView

class HomePageList : AppCompatActivity(), PokemonAdapter.CellClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_list)

        try{
            this.supportActionBar?.hide();
        }catch (NullPointerException: Error){}

        val page = 0

        doAsync {
            val pokeApi = PokeApiClient()
            val pokemons = pokeApi.getPokemonList(page*20, 20)
            uiThread {
                val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                recyclerView.adapter = PokemonAdapter(pokemons.results, it, it)
                findViewById<GifImageView>(R.id.gif_loading).visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCellClickListener(data: NamedApiResource) {
        val intent = Intent(this, PokemonActivity::class.java)
        intent.putExtra("PokemonId", data.id.toString())
        startActivity(intent)
    }
}
