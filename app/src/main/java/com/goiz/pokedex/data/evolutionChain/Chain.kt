package com.gois.pokedex.data.evolutionChain


import com.google.gson.annotations.SerializedName

data class Chain(
    @SerializedName("evolution_details")
    val evolutionDetails: List<EvolutionDetail>,
    @SerializedName("evolves_to")
    val evolvesTo: List<Chain>,
    @SerializedName("is_baby")
    val isBaby: Boolean,
    val species: Species
)