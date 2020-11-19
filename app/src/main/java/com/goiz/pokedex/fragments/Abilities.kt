package com.goiz.pokedex.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goiz.pokedex.R
import com.goiz.pokedex.adapters.AbilityAdapter
import com.goiz.pokedex.model.Abilities
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import me.sargunvohra.lib.pokekotlin.model.Ability
import me.sargunvohra.lib.pokekotlin.model.PokemonAbility
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.uiThread

class Abilities : Fragment() {

    lateinit var rcAbilites: RecyclerView
    private val pokeApi = PokeApiClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_abilities, container, false)
        val abilities = arguments?.getSerializable("Abilities") as Abilities

        rcAbilites = view.findViewById(R.id.rcAbilities)
        rcAbilites.adapter = AbilityAdapter(abilities.abilities, requireContext())

        return view
    }
}