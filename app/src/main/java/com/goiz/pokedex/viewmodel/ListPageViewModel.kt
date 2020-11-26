package com.goiz.pokedex.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.goiz.pokedex.data.PokeService
import com.goiz.pokedex.data.pokemon.Pokemon
import kotlinx.coroutines.launch
import java.lang.Exception

class ListPageViewModel(private val pokemonService: PokeService): ViewModel() {

    private val _pokemonsData = MutableLiveData<List<Pokemon>>()
    val pokemonsData: LiveData<List<Pokemon>>
        get() = _pokemonsData

    fun getPokemons(){
        viewModelScope.launch {
            val pokemonList = mutableListOf<Pokemon>()
            try {
                val chainsResponse = pokemonService.getEvolutionChainList()
                if(chainsResponse.isSuccessful) {
                    chainsResponse.body()?.let { chains ->
                        for (chainData in chains.results) {
                            val chainResponse = pokemonService.getEvolutionChain(
                                chainData.url
                                    .substringAfterLast("chain/")
                                    .split("/")[0]
                                    .toInt()
                            )
                            if (chainResponse.isSuccessful) {
                                chainResponse.body()?.let { chain ->
                                    val pokemonResponse =
                                        pokemonService.getPokemon(chain.chain.species.name)
                                    if (pokemonResponse.isSuccessful) {
                                        pokemonResponse.body()?.let { pokemon ->
                                            pokemonList.add(pokemon)
                                        }
                                    }
                                }
                            }
                        }
                        _pokemonsData.value = pokemonList.toList()
                    }
                }
            }catch (e: Exception){
                Log.d("Service Error:", e.toString())
            }
        }
    }
    fun getPokemon(id: Int){
        viewModelScope.launch {
            val pokemon = pokemonService.getPokemon(id)
            if(pokemon.isSuccessful){
                pokemon.body()?.let {
                    _pokemonsData.value = listOf(it)
                }
            }
        }
    }
    fun getPokemon(name: String){
        viewModelScope.launch {
            val pokemon = pokemonService.getPokemon(name)
            if(pokemon.isSuccessful){
                pokemon.body()?.let {
                    _pokemonsData.value = listOf(it)
                }
            }
        }
    }

    class MainViewModelFactory(private val repository: PokeService) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ListPageViewModel(repository) as T
        }

    }
}