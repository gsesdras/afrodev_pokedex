package com.gois.pokedex.data.evolutionChainList


data class EvolutionChainList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Chain>
)