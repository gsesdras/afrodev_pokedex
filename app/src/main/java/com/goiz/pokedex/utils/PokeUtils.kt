package com.goiz.pokedex.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import java.util.*


class PokeUtils {

    fun capitalize(string: String) : String{
        val result = string.substring(0, 1).toUpperCase(Locale.ROOT) + (string.substring(1));
        return result
    }
    fun getImageByString(c: Context, imageName: String?): Drawable? {
        return ContextCompat.getDrawable(c, c.resources.getIdentifier(imageName, "drawable", c.packageName));
    }
    fun idMask(id: Int): String {
        id.let{
            return when(id){
                in 0..10 -> "#00${id}"
                in 11..99 -> "#0${id}"
                else -> "#${id}"
            }
        }
    }
}