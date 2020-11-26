package com.goiz.pokedex.data

import com.gois.pokedex.data.evolutionChain.EvolutionChain
import com.gois.pokedex.data.evolutionChainList.EvolutionChainList
import com.goiz.pokedex.data.pokemon.Pokemon
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeService {
    private val api: PokeApi = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokeApi::class.java)

    suspend fun getEvolutionChainList() : Response<EvolutionChainList> = api.getEvolutionChainList()
    suspend fun getEvolutionChain(id: Int) : Response<EvolutionChain> = api.getEvolutionChain(id.toString())
    suspend fun getPokemon(id: Int) : Response<Pokemon> = api.getPokemon(id.toString())
    suspend fun getPokemon(name: String) : Response<Pokemon> = api.getPokemon(name)
}