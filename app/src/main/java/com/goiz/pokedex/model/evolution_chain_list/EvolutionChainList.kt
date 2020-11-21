package com.goiz.pokedex.model.evolution_chain_list

data class EvolutionChainList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)