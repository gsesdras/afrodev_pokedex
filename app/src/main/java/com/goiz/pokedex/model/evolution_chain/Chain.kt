package com.goiz.pokedex.model.evolution_chain

data class Chain(
    val evolution_details: List<Any>,
    val evolves_to: List<Chain>,
    val is_baby: Boolean,
    val species: Species
)