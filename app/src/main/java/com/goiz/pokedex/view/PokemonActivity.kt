package com.goiz.pokedex.view

import TabAdapter
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager.widget.ViewPager
import com.goiz.pokedex.R
import com.goiz.pokedex.model.Abilities
import com.goiz.pokedex.model.ability.Ability
import com.goiz.pokedex.model.evolution_chain.Chain
import com.goiz.pokedex.model.evolution_chain.EvolutionChain
import com.goiz.pokedex.model.pokemon.Pokemon
import com.goiz.pokedex.model.type.Type
import com.goiz.pokedex.util.PokeUtils
import com.goiz.pokedex.viewmodel.PokemonViewModel
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import pl.droidsonroids.gif.GifImageView


class PokemonActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    private val evolutions = arrayListOf<Int>()
    private val evolutionsName = arrayListOf<String>()
    private val baseURL =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"
    private val pokemonIcon: ImageView by lazy { findViewById(R.id.pokemon_icon_main) }
    private val pokemonName: TextView by lazy { findViewById(R.id.pokemon_name_main) }
    private val pokemonId: TextView by lazy { findViewById(R.id.pokemon_id_main) }
    private val loading: GifImageView by lazy { findViewById(R.id.gif_loading) }
    private val view: ConstraintLayout by lazy { findViewById(R.id.pokemon_view) }
    private val tag: ImageView by lazy { findViewById(R.id.tag) }
    private val list: List<View> by lazy { listOf(pokemonIcon, pokemonName, pokemonId, tag) }

    private val viewModel = PokemonViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pokemonType: String = intent.getStringExtra("PokemonType").toString()
        val pokemonIdExtra: String = intent.getStringExtra("PokemonId").toString()

        val theme = PokeUtils.getThemeForType(pokemonType)
        setTheme(theme)

        setContentView(R.layout.activity_pokemon)

        showLoading(loading, list, view)

        getData(pokemonIdExtra)
    }

    private fun showLoading(loading: GifImageView, items: List<View>, view: ConstraintLayout) {
        items.forEach {
            it.visibility = View.INVISIBLE
        }
        view.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        loading.visibility = View.VISIBLE
    }

    private fun hideLoading(loading: GifImageView, items: List<View>, view: ConstraintLayout) {
        items.forEach {
            it.visibility = View.VISIBLE
        }

        view.background =
            ResourcesCompat.getDrawable(resources, R.drawable.background_rounded_white, null)
        loading.visibility = View.GONE
    }

    private fun setupTabLayout(bundle: Bundle) {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        tabLayout.removeAllTabs()
        tabLayout.addTab(tabLayout.newTab().setText("Status"))
        tabLayout.addTab(tabLayout.newTab().setText("Evoluções"))
        tabLayout.addTab(tabLayout.newTab().setText("Habilidades"))

        val adapter = TabAdapter(
            this@PokemonActivity,
            supportFragmentManager,
            tabLayout.tabCount,
            bundle
        )

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

    fun addChain(chain: Chain, evolutions: ArrayList<Int>, evolutionsName: ArrayList<String>) {
        if (chain.evolves_to.isNullOrEmpty())
            return
        if(evolutions.isEmpty()){
            evolutions.add(PokeUtils.getIdfromUrl(chain.species.url, "species/").toInt())
        }
        if(evolutionsName.isEmpty()){
            evolutionsName.add(chain.species.name)
        }


        val evolvesTo = chain.evolves_to[0]

        val id = PokeUtils.getIdfromUrl(evolvesTo.species.url, "species/").toInt()

        evolutions.add(id)
        evolutionsName.add(evolvesTo.species.name)


        if (!evolvesTo.evolves_to.isNullOrEmpty()) {
            addChain(evolvesTo, evolutions, evolutionsName)
        }
    }

    private fun getData(id: String) {
        viewModel.getPokemonByName(id).observe(this, { pokemon ->
            viewModel.getTypeDetails(pokemon.types[0].type.name).observe(this, { type ->
                viewModel.getPokemonSpecies(pokemon.id.toString()).observe(this, { species ->
                    viewModel.getEvolutionChain(species.evolution_chain.url.substringAfterLast("evolution-chain/"))
                        .observe(this, { chain ->
                            val mAbilities = mutableListOf<Ability>()
                            for (abilityData in pokemon.abilities) {
                                viewModel.getAbility(abilityData.ability.url.substringAfterLast("ability/"))
                                    .observe(this, { ability ->
                                        mAbilities.add(ability)
                                        setUI(
                                            pokemon,
                                            type,
                                            chain,
                                            mAbilities,
                                            pokemon.abilities.size
                                        )
                                    })
                            }
                        })
                })
            })
        })
    }

    private fun setUI(
        pokemon: Pokemon,
        type: Type,
        chain: EvolutionChain,
        abilities: MutableList<Ability>,
        abilitiesCount: Int
    ) {

        if (abilities.size != abilitiesCount)
            return

        val name = PokeUtils.capitalize(pokemon.name)
        pokemonName.text = name
        pokemonId.text = PokeUtils.idMask(pokemon.id)

        val imageURL = "$baseURL${pokemon.id}.png"
        Picasso.get().load(imageURL).into(pokemonIcon)

        val tagName = "tag_${pokemon.types[0].type.name}"
        val tagSource = PokeUtils.getImageByString(this, tagName)
        tag.setImageDrawable(tagSource)


        val pokemonStat = hashMapOf<String, Int>()
        pokemonStat["HP"] = pokemon.stats[0].base_stat
        pokemonStat["ATK"] = pokemon.stats[1].base_stat
        pokemonStat["DEF"] = pokemon.stats[2].base_stat
        pokemonStat["SATK"] = pokemon.stats[3].base_stat
        pokemonStat["SDEF"] = pokemon.stats[4].base_stat
        pokemonStat["SPD"] = pokemon.stats[5].base_stat

        val weaknesses = arrayListOf<String>()
        val resistances = arrayListOf<String>()

        type.damage_relations.half_damage_to.forEach {
            weaknesses.add(it.name)
        }
        type.damage_relations.half_damage_from.forEach {
            resistances.add(it.name)
        }

        addChain(chain.chain, evolutions, evolutionsName)

        val bundle = Bundle().apply {
            putSerializable("PokemonStats", pokemonStat)
            putStringArrayList("Weaknesses", weaknesses)
            putStringArrayList("Resistances", resistances)
            putIntegerArrayList("Evolutions", evolutions)
            putStringArrayList("EvolutionsName", evolutionsName)
            putInt("PokemonId", pokemon.id)
            putString("PokemonName", pokemon.name)
            putSerializable("Abilities", Abilities(abilities))
        }

        setupTabLayout(bundle)
        hideLoading(loading, list, view)
    }
}