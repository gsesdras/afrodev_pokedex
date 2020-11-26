package com.gois.pokedex.data.evolutionChain


import com.google.gson.annotations.SerializedName

data class EvolutionChain(
    @SerializedName("baby_trigger_item")
    val babyTriggerItem: Any,
    val chain: Chain,
    val id: Int
)