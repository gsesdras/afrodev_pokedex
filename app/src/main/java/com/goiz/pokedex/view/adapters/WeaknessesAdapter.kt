package com.goiz.pokedex.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.goiz.pokedex.R
import com.goiz.pokedex.util.PokeUtils


class WeaknessesAdapter(
    private val weaknessesList: MutableList<String>,
    private val context: Context
) : RecyclerView.Adapter<WeaknessesAdapter.WeaknessesViewHolder>() {
    class WeaknessesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgWeakness = itemView.findViewById<ImageView>(R.id.imgWeakness)

        fun bind(weakness: String, c: Context) {
            val source = "tag_$weakness"
            val image = PokeUtils.getImageByString(c, source)
            imgWeakness.setImageDrawable(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaknessesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weaknesses_item, parent, false)

        return WeaknessesViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeaknessesViewHolder, position: Int) {
        holder.bind(weaknessesList[position], context)
    }

    override fun getItemCount(): Int {
        return weaknessesList.size
    }
}