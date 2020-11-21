package com.goiz.pokedex.model.pokemon

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)