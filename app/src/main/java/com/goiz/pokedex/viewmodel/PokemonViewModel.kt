package com.goiz.pokedex.viewmodel

import androidx.lifecycle.MutableLiveData
import com.goiz.pokedex.model.ability.Ability
import com.goiz.pokedex.model.evolution_chain.EvolutionChain
import com.goiz.pokedex.model.pokemon.Pokemon
import com.goiz.pokedex.model.species.Species
import com.goiz.pokedex.model.type.Type
import com.goiz.pokedex.res.respository.PokemonRepository

class PokemonViewModel(
    private val repository: PokemonRepository = PokemonRepository()
) {

    fun getPokemonByName(name: String): MutableLiveData<Pokemon> {
        return repository.getPokemonByName(name)
    }

    fun getTypeDetails(id: String): MutableLiveData<Type> {
        return repository.getTypeDetails(id)
    }

    fun getPokemonSpecies(id: String): MutableLiveData<Species> {
        return repository.getPokemonSpecies(id)
    }

    fun getEvolutionChain(id: String): MutableLiveData<EvolutionChain> {
        return repository.getEvolutionChain(id)
    }

    fun getAbility(id: String): MutableLiveData<Ability> {
        return repository.getAbility(id)
    }
}