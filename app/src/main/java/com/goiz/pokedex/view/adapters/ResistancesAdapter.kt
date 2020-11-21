package com.goiz.pokedex.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.goiz.pokedex.R
import com.goiz.pokedex.util.PokeUtils

class ResistancesAdapter(
    private val resistancesList: MutableList<String>,
    private val context: Context
) : RecyclerView.Adapter<ResistancesAdapter.ResistancesViewHolder>() {
    class ResistancesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgResist = itemView.findViewById<ImageView>(R.id.imgResistances)

        fun bind(resistance: String, c: Context) {
            val source = "tag_$resistance"
            val image = PokeUtils.getImageByString(c, source)
            imgResist.setImageDrawable(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResistancesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.resistances_item, parent, false)

        return ResistancesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResistancesViewHolder, position: Int) {
        holder.bind(resistancesList[position], context)
    }

    override fun getItemCount(): Int {
        return resistancesList.size
    }
}