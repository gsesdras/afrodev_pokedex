package com.goiz.pokedex

import TabAdapter
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.goiz.pokedex.utils.PokeUtils
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import pl.droidsonroids.gif.GifImageView


class PokemonActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    private val pokemonStat = hashMapOf<String, Int>()
    private val weaknesses = arrayListOf<String>()
    private val resistances = arrayListOf<String>()
    private val baseURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"
    private val pokeApi = PokeApiClient()

    private fun showLoading(loading: GifImageView, items: List<View>, view: ConstraintLayout){
        items.forEach{
            it.visibility = View.INVISIBLE
        }
        view.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        loading.visibility = View.VISIBLE
    }
    private fun hideLoading(loading: GifImageView, items: List<View>, view: ConstraintLayout){
        items.forEach{
            it.visibility = View.VISIBLE
        }
        view.setBackgroundDrawable(resources.getDrawable(R.drawable.background_rounded_white))
        loading.visibility = View.GONE
    }
    private fun setupTabLayout(){
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        tabLayout.addTab(tabLayout.newTab().setText("Status"))
        tabLayout.addTab(tabLayout.newTab().setText("Evoluções"))
        tabLayout.addTab(tabLayout.newTab().setText("Habilidades"))

        val bundle = Bundle().apply {
            putSerializable("PokemonStats", pokemonStat)
            putStringArrayList("Weaknesses", weaknesses)
            putStringArrayList("Resistances", resistances)
        }

        val adapter = TabAdapter(
                this@PokemonActivity,
                supportFragmentManager,
                tabLayout.tabCount,
                bundle)

        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        val pokemonIcon: ImageView = findViewById(R.id.pokemon_icon_main)
        val pokemonName: TextView = findViewById(R.id.pokemon_name_main)
        val pokemonId: TextView = findViewById(R.id.pokemon_id_main)
        val loading: GifImageView = findViewById(R.id.gif_loading)
        val view: ConstraintLayout = findViewById(R.id.pokemon_view)
        val pokemonIdExtra: String = intent.getStringExtra("PokemonId").toString()
        val list: List<View> = listOf(pokemonIcon, pokemonName, pokemonId)

        showLoading(loading, list, view)

        doAsync{
            val pokemon = pokemonIdExtra.let{ pokeApi.getPokemon(pokemonIdExtra.toInt())}
            val type = pokeApi.getType(pokemon.types[0].type.id)
            uiThread {
                pokemon.let{
                    val name = PokeUtils.capitalize(pokemon.name)

                    val imageURL = "$baseURL${pokemon.id}.png"
                    Picasso.get().load(imageURL).into(pokemonIcon)

                    pokemonName.text = name
                    pokemonId.text = PokeUtils.idMask(pokemon.id)

                    pokemonStat["HP"] = pokemon.stats[0].baseStat
                    pokemonStat["ATK"] = pokemon.stats[1].baseStat
                    pokemonStat["DEF"] = pokemon.stats[2].baseStat
                    pokemonStat["SATK"] = pokemon.stats[3].baseStat
                    pokemonStat["SDEF"] = pokemon.stats[4].baseStat
                    pokemonStat["SPD"] = pokemon.stats[5].baseStat

                    type.damageRelations.halfDamageTo.forEach{
                        weaknesses.add(it.name)
                    }
                    type.damageRelations.halfDamageFrom.forEach{
                        resistances.add(it.name)
                    }

                    setupTabLayout()
                    hideLoading(loading, list, view)
                }
            }
        }
    }
}