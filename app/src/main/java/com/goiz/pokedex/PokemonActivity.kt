package com.goiz.pokedex

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.goiz.pokedex.utils.PokeUtils
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import pl.droidsonroids.gif.GifImageView
import java.util.*

class PokemonActivity : AppCompatActivity() {
    private val pokeUtils = PokeUtils()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)
        val pokeUtils = PokeUtils()

        val loading = findViewById<GifImageView>(R.id.gif_loading)
        val pokemonIdExtra = intent.getStringExtra("PokemonId")
        val view = findViewById<ConstraintLayout>(R.id.pokemon_view)
        val pokemonIcon = findViewById<ImageView>(R.id.pokemon_icon)
        val pokemonName = findViewById<TextView>(R.id.pokemon_name)
        val pokemonId = findViewById<TextView>(R.id.pokemon_id)

        view.visibility = View.GONE
        pokemonIcon.visibility = View.GONE
        loading.visibility = View.VISIBLE

        doAsync{
            val pokeApi = PokeApiClient()
            val pokemon = pokemonIdExtra?.let { pokeApi.getPokemon(pokemonIdExtra.toInt()) }
            uiThread {
                pokemon?.let{
                    val name = pokeUtils.capitalize(pokemon.name)

                    val imageURL =
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemon.id}.png"
                    Picasso.get().load(imageURL).into(pokemonIcon)

                    pokemonName.text = name
                    pokemonId.text = pokeUtils.idMask(pokemon.id)
                    loading.visibility = View.GONE
                    view.visibility = View.VISIBLE
                    pokemonIcon.visibility = View.VISIBLE

                }
            }
        }
    }
}