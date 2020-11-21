package com.goiz.pokedex.res.respository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.goiz.pokedex.model.ability.Ability
import com.goiz.pokedex.model.evolution_chain.EvolutionChain
import com.goiz.pokedex.model.pokemon.Pokemon
import com.goiz.pokedex.model.species.Species
import com.goiz.pokedex.model.type.Type
import com.goiz.pokedex.res.respository.services.PokemonService
import com.goiz.pokedex.util.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepository {

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

    fun getTypeDetails(id: String): MutableLiveData<Type> {

        val retrofit = NetworkUtils.retrofitConfig()

        val pokemonService: PokemonService = retrofit.create(PokemonService::class.java)

        val callback: Call<Type> = pokemonService.getTypeDetails(id)

        val mutableLiveData = MutableLiveData<Type>()

        callback.enqueue(object : Callback<Type> {
            override fun onResponse(
                call: Call<Type>,
                response: Response<Type>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<Type>, t: Throwable) {
                Log.d("Erro!", "Deu erro, mané")
            }

        })

        return mutableLiveData
    }

    fun getPokemonSpecies(id: String): MutableLiveData<Species> {

        val retrofit = NetworkUtils.retrofitConfig()

        val pokemonService: PokemonService = retrofit.create(PokemonService::class.java)

        val callback: Call<Species> = pokemonService.getPokemonSpecies(id)

        val mutableLiveData = MutableLiveData<Species>()

        callback.enqueue(object : Callback<Species> {
            override fun onResponse(
                call: Call<Species>,
                response: Response<Species>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<Species>, t: Throwable) {
                Log.d("Erro!", "Deu erro, mané")
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

    fun getAbility(id: String): MutableLiveData<Ability> {
        val retrofit = NetworkUtils.retrofitConfig()

        val pokemonService = retrofit.create(PokemonService::class.java)

        val callback: Call<Ability> = pokemonService.getAbility(id)

        val mutableLiveData = MutableLiveData<Ability>()

        callback.enqueue(object : Callback<Ability> {
            override fun onResponse(call: Call<Ability>, response: Response<Ability>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<Ability>, t: Throwable) {
                Log.d("Erro!", "Deu erro, mané!")
            }

        })

        return mutableLiveData
    }
}