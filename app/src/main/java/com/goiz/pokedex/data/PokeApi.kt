package com.goiz.pokedex.data

import com.gois.pokedex.data.evolutionChain.EvolutionChain
import com.gois.pokedex.data.evolutionChainList.EvolutionChainList
import com.goiz.pokedex.data.pokemon.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {
    @GET("evolution-chain")
    suspend fun getEvolutionChainList() : Response<EvolutionChainList>

    @GET("evolution-chain/{id}")
    suspend fun getEvolutionChain(@Path("id") id: String) : Response<EvolutionChain>

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: String) : Response<Pokemon>
}