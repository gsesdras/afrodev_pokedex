package com.goiz.pokedex.model.pokemon

import com.google.gson.annotations.SerializedName

data class GenerationVi(
    @SerializedName("omegaruby-alphasapphire")
    val omegaruby_lphasapphire: OmegarubyAlphasapphire,
    @SerializedName("x-y")
    val x_y: XY
)