package com.goiz.pokedex.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.goiz.pokedex.model.evolution_chain.EvolutionChain
import com.goiz.pokedex.model.evolution_chain_list.EvolutionChainList
import com.goiz.pokedex.model.pokemon.Pokemon
import com.goiz.pokedex.model.pokemon_list.PokemonList
import com.goiz.pokedex.res.respository.ListPageRepository

class ListPageViewModel(private val repository: ListPageRepository): ViewModel() {

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

    class ListPageViewModelFactory(private val repository: ListPageRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ListPageViewModel(repository) as T
        }

    }
}