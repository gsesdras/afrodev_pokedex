package com.goiz.pokedex.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.ContextCompat
import java.util.*


object PokeUtils {

    fun capitalize(string: String) : String{
        val result = string.substring(0, 1).toUpperCase(Locale.ROOT) + (string.substring(1));
        return result
    }
    fun getImageByString(c: Context, imageName: String?): Drawable? {
        val image = ContextCompat.getDrawable(c, c.resources.getIdentifier(imageName, "drawable", c.packageName));
        return image
    }
    fun idMask(id: Int): String {
        id.let{
            return when(id){
                in 0..10 -> "#00${id}"
                in 11..99 -> "#0${id}"
                else -> "#$id"
            }
        }
    }
    fun idStatMask(stat: Int): String {
        stat.let{
            return when(stat){
                in 0..10 -> "00${stat}"
                in 11..99 -> "0${stat}"
                else -> "$stat"
            }
        }
    }
}