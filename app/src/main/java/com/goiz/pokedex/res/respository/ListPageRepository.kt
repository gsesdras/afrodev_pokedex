package com.goiz.pokedex.res.respository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.goiz.pokedex.model.evolution_chain.EvolutionChain
import com.goiz.pokedex.model.evolution_chain_list.EvolutionChainList
import com.goiz.pokedex.model.pokemon.Pokemon
import com.goiz.pokedex.model.pokemon_list.PokemonList
import com.goiz.pokedex.res.respository.services.PokemonService
import com.goiz.pokedex.util.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListPageRepository {
    fun getPokemonList(): MutableLiveData<PokemonList> {
        val retrofit = NetworkUtils.retrofitConfig()

        val pokemonService = retrofit.create(PokemonService::class.java)

        val callback: Call<PokemonList> = pokemonService.getPokemonList()

        val mutableLiveData = MutableLiveData<PokemonList>()

        callback.enqueue(object : Callback<PokemonList> {
            override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                Log.d("Erro!", "Deu erro, mané!")
            }

        })

        return mutableLiveData
    }

    fun getPokemonByName(name: String): MutableLiveData<Pokemon> {

        val retrofit = NetworkUtils.retrofitConfig()

        val pokemonService: PokemonService = retrofit.create(PokemonService::class.java)

        val callback: Call<Pokemon> = pokemonService.getPokemonByName(name)

        val mutableLiveData = MutableLiveData<Pokemon>()

        callback.enqueue(object : Callback<Pokemon> {
            override fun onResponse(
                call: Call<Pokemon>,
                response: Response<Pokemon>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Log.d("Erro!", "Deu erro, mané")
            }

        })

        return mutableLiveData
    }

    fun getEvolutionChainList(): MutableLiveData<EvolutionChainList> {
        val retrofit = NetworkUtils.retrofitConfig()

        val pokemonService = retrofit.create(PokemonService::class.java)

        val callback: Call<EvolutionChainList> = pokemonService.getEvolutionChainList()

        val mutableLiveData = MutableLiveData<EvolutionChainList>()

        callback.enqueue(object : Callback<EvolutionChainList> {
            override fun onResponse(
                call: Call<EvolutionChainList>,
                response: Response<EvolutionChainList>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<EvolutionChainList>, t: Throwable) {
                Log.d("Erro!", "Deu erro, mané!")
            }

        })

        return mutableLiveData
    }

    fun getEvolutionChain(id: String): MutableLiveData<EvolutionChain> {
        val retrofit = NetworkUtils.retrofitConfig()

        val pokemonService = retrofit.create(PokemonService::class.java)

        val callback: Call<EvolutionChain> = pokemonService.getEvolutionChain(id)

        val mutableLiveData = MutableLiveData<EvolutionChain>()

        callback.enqueue(object : Callback<EvolutionChain> {
            override fun onResponse(
                call: Call<EvolutionChain>,
                response: Response<EvolutionChain>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<EvolutionChain>, t: Throwable) {
                Log.d("Erro!", "Deu erro, mané!")
            }

        })

        return mutableLiveData
    }
}