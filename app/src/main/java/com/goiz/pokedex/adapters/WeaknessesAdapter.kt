package com.goiz.pokedex.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.goiz.pokedex.R
import com.goiz.pokedex.utils.PokeUtils

class WeaknessesAdapter(
        private val weaknessesList: MutableList<String>,
        private val context: Context
) : RecyclerView.Adapter<WeaknessesAdapter.WeaknessesViewHolder>() {
    class WeaknessesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val imgWeakness = itemView.findViewById<ImageView>(R.id.imgWeakness)
        private val txtWeakness = itemView.findViewById<TextView>(R.id.txtWeakness)

        fun bind(weakness: String) {
            val image = itemView.resources.getIdentifier("com.goiz.pokedex:drawable/$weakness", null, null)
            imgWeakness.setImageDrawable(itemView.resources.getDrawable(image))
            txtWeakness.text = PokeUtils.capitalize(weakness)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaknessesViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.weakness_item, parent, false)

        return WeaknessesViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeaknessesViewHolder, position: Int) {
        holder.bind(weaknessesList[position])
    }

    override fun getItemCount(): Int {
        return weaknessesList.size
    }
}