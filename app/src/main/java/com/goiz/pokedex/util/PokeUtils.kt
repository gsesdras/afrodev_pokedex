package com.goiz.pokedex.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.goiz.pokedex.R
import java.util.*


object PokeUtils {

    fun capitalize(string: String): String {
        return string.substring(0, 1).toUpperCase(Locale.ROOT) + (string.substring(1))
    }

    fun getImageByString(c: Context, imageName: String?): Drawable? {
        return ContextCompat.getDrawable(
            c,
            c.resources.getIdentifier(imageName, "drawable", c.packageName)
        )
    }

    fun idMask(id: Int): String {
        id.let {
            return when (id) {
                in 1..9 -> "#00${id}"
                in 10..99 -> "#0${id}"
                else -> "#$id"
            }
        }
    }

    fun idStatMask(stat: Int): String {
        stat.let {
            return when (stat) {
                in 0..10 -> "00${stat}"
                in 11..99 -> "0${stat}"
                else -> "$stat"
            }
        }
    }

    fun getThemeForType(type: String): Int {
        val returnId: Int

        when (type) {
            "bug" -> returnId = R.style.bug
            "dark" -> returnId = R.style.dark
            "dragon" -> returnId = R.style.dragon
            "electric" -> returnId = R.style.electric
            "fairy" -> returnId = R.style.fairy
            "fighting" -> returnId = R.style.fighting
            "fire" -> returnId = R.style.fire
            "flying" -> returnId = R.style.flying
            "ghost" -> returnId = R.style.ghost
            "grass" -> returnId = R.style.grass
            "ground" -> returnId = R.style.ground
            "ice" -> returnId = R.style.ice
            "steel" -> returnId = R.style.steel
            "normal" -> returnId = R.style.normal
            "poison" -> returnId = R.style.poison
            "psychic" -> returnId = R.style.psychic
            "rock" -> returnId = R.style.rock
            "water" -> returnId = R.style.water
            else -> returnId = R.style.normal
        }

        return returnId
    }

    fun getIdfromUrl(str: String, delimiter1: String): String{
        return str.substringAfterLast(delimiter1).split("/")[0]
    }
}