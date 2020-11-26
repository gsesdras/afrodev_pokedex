package com.goiz.pokedex.data.pokemon


import com.google.gson.annotations.SerializedName

data class VersionDetail(
    val rarity: Int,
    val version: VersionX
)