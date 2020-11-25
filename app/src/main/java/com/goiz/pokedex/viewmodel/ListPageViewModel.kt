package com.goiz.pokedex.viewmodel

import androidx.lifecycle.MutableLiveData
import com.goiz.pokedex.model.evolution_chain.EvolutionChain
import com.goiz.pokedex.model.evolution_chain_list.EvolutionChainList
import com.goiz.pokedex.model.pokemon.Pokemon
import com.goiz.pokedex.model.pokemon_list.PokemonList
import com.goiz.pokedex.res.respository.ListPageRepository

class ListPageViewModel(
    private val repository: ListPageRepository = ListPageRepository()
) {

    fun getPokemonList(): MutableLiveData<PokemonList> {
        return repository.getPokemonList()
    }

    fun getPokemonByName(name: String): MutableLiveData<Pokemon> {
        return repository.getPokemonByName(name)
    }

    fun getEvolutionChainList(): MutableLiveData<EvolutionChainList> {
        return repository.getEvolutionChainList()
    }

    fun getEvolutionChain(id: String): MutableLiveData<EvolutionChain> {
        return repository.getEvolutionChain(id)
    }
}