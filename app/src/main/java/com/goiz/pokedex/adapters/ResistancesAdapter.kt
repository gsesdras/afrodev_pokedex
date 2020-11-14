package com.goiz.pokedex.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.goiz.pokedex.R
import com.goiz.pokedex.utils.PokeUtils

class ResistancesAdapter(
        private val resistancesList: MutableList<String>,
        private val context: Context
) : RecyclerView.Adapter<ResistancesAdapter.ResistancesViewHolder>() {
    class ResistancesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val imgResist = itemView.findViewById<ImageView>(R.id.imgResistances)
        private val txtResist = itemView.findViewById<TextView>(R.id.txtResist)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(resistance: String, c: Context) {
            try {
                val uri = "@drawable/$resistance"
                val imageRes = itemView.resources.getIdentifier(uri, null, c.packageName)
                val res: Drawable = itemView.resources.getDrawable(imageRes)
                imgResist.setImageDrawable(res)
            }catch (err: Error){
                Log.d("QUE QUE ISSO", err.toString())
            }
            txtResist.text = PokeUtils.capitalize(resistance)
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