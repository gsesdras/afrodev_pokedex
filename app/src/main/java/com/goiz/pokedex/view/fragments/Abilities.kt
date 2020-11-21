package com.goiz.pokedex.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.goiz.pokedex.R
import com.goiz.pokedex.model.Abilities
import com.goiz.pokedex.view.adapters.AbilityAdapter

class Abilities : Fragment() {

    lateinit var rcAbilites: RecyclerView

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