package com.goiz.pokedex

import TabAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import com.goiz.pokedex.utils.PokeUtils
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import pl.droidsonroids.gif.GifImageView

class PokemonActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        val pokeUtils = PokeUtils()
        val pokeApi = PokeApiClient()
        val baseURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

        val loading: GifImageView = findViewById(R.id.gif_loading)
        val pokemonIdExtra: String = intent.getStringExtra("PokemonId").toString()
        val view: ConstraintLayout = findViewById(R.id.pokemon_view)
        val pokemonIcon: ImageView = findViewById(R.id.pokemon_icon)
        val pokemonName: TextView = findViewById(R.id.pokemon_name)
        val pokemonId: TextView = findViewById(R.id.pokemon_id)

        // TAB NAVIGATION
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab().setText("Status"))
        tabLayout.addTab(tabLayout.newTab().setText("Evoluções"))
        tabLayout.addTab(tabLayout.newTab().setText("Habilidades"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = TabAdapter(this, supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        // INITIAL VISIBILITY
        view.setBackgroundColor(resources.getColor(R.color.white))
        pokemonIcon.visibility = View.GONE
        pokemonName.visibility = View.GONE
        pokemonId.visibility = View.GONE
        tabLayout.visibility = View.GONE
        viewPager.visibility = View.GONE
        loading.visibility = View.VISIBLE

        doAsync{
            val pokemon = pokemonIdExtra.let { pokeApi.getPokemon(pokemonIdExtra.toInt()) }
            uiThread {
                pokemon.let{
                    val name = pokeUtils.capitalize(pokemon.name)
                    val imageURL =
                        "$baseURL${pokemon.id}.png"
                    Picasso.get().load(imageURL).into(pokemonIcon)
                    pokemonName.text = name
                    pokemonId.text = pokeUtils.idMask(pokemon.id)


                    // FINAL VISIBILITY
                    loading.visibility = View.GONE
                    view.background = resources.getDrawable(R.drawable.background_rounded_white)
                    pokemonIcon.visibility = View.VISIBLE
                    pokemonName.visibility = View.VISIBLE
                    pokemonId.visibility = View.VISIBLE
                    tabLayout.visibility = View.VISIBLE
                    viewPager.visibility = View.VISIBLE


                }
            }
        }
    }
}