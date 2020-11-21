package com.goiz.pokedex.res.respository.services

import com.goiz.pokedex.model.ability.Ability
import com.goiz.pokedex.model.evolution_chain.EvolutionChain
import com.goiz.pokedex.model.evolution_chain_list.EvolutionChainList
import com.goiz.pokedex.model.pokemon.Pokemon
import com.goiz.pokedex.model.pokemon_list.PokemonList
import com.goiz.pokedex.model.species.Species
import com.goiz.pokedex.model.type.Type
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon")
    fun getPokemonList(): Call<PokemonList>

    @GET("pokemon/{name}")
    fun getPokemonByName(@Path("name") name: String): Call<Pokemon>

    @GET("evolution-chain")
    fun getEvolutionChainList(): Call<EvolutionChainList>

    @GET("evolution-chain/{id}")
    fun getEvolutionChain(@Path("id") id: String): Call<EvolutionChain>

    @GET("type/{id}")
    fun getTypeDetails(@Path("id") id: String): Call<Type>

    @GET("pokemon-species/{id}")
    fun getPokemonSpecies(@Path("id") id: String): Call<Species>

    @GET("ability/{id}")
    fun getAbility(@Path("id") id: String): Call<Ability>
}
