package com.goiz.pokedex.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.goiz.pokedex.R
import com.goiz.pokedex.adapters.EvolutionAdapter
import com.goiz.pokedex.adapters.ResistancesAdapter

class Evolution : Fragment() {
    private lateinit var rcEvolution: RecyclerView
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_evolution, container, false)

        val pokemonId = arguments?.getInt("PokemonId")
        val pokemonName = arguments?.getString("PokemonName")
        val evolutionData = arguments?.getIntegerArrayList("Evolutions")
        val evolutionsNameData = arguments?.getStringArrayList("EvolutionsName")

        val evolutionList = mutableListOf<Int>().apply {
            if (pokemonId != null) {
                add(pokemonId)
            }
        }
        val evolutionListName = mutableListOf<String>().apply {
            if (pokemonName != null) {
                add(pokemonName)
            }
        }


        evolutionData?.forEach {
            evolutionList.add(it)
        }
        evolutionsNameData?.forEach {
            evolutionListName.add(it)
        }

        val mutableEvolution = mutableListOf<List<String>>()

        for (id in evolutionList.indices) {
            val next = id + 1
            if (next < evolutionList.size) {
                mutableEvolution.add(listOf(
                        evolutionList[id].toString(),
                        evolutionList[next].toString(),
                        evolutionListName[id],
                        evolutionListName[next]
                ))
            }
        }

        rcEvolution = view.findViewById(R.id.rcEvolution)
        rcEvolution.adapter = EvolutionAdapter(mutableEvolution, requireContext())


        return view
    }
}