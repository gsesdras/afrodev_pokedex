package com.goiz.pokedex.model

import java.io.Serializable

data class Pokemon(
        val id: Int,
        val name: String,
        val type: String,
        val chain: Int
) : Serializable