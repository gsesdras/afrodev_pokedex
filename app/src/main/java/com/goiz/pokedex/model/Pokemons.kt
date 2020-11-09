package com.goiz.pokedex.model

import android.graphics.drawable.Drawable
import com.goiz.pokedex.R

/* Returns initial list of flowers. */
fun pokemonList(): List<Pokemon> {
    return listOf(
            Pokemon(
                    id = 25,
                    name = "Pikachu"
            ),
            Pokemon(
                    id = 4,
                    name = "Charmander"
            ),
            Pokemon(
                    id = 132,
                    name = "Ditto"
            )
    )
}