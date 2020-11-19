package com.goiz.pokedex.api

import com.goiz.pokedex.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

        @GET("pokemon/{pokemon}")
        fun getPokemonByName(@Path("pokemon") pokemon: String) : Call<PokemonResponse>
}
