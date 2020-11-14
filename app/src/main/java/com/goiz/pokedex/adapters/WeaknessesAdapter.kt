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


class WeaknessesAdapter(
        private val weaknessesList: MutableList<String>,
        private val context: Context
) : RecyclerView.Adapter<WeaknessesAdapter.WeaknessesViewHolder>() {
    class WeaknessesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val imgWeakness = itemView.findViewById<ImageView>(R.id.imgWeakness)
        private val txtWeakness = itemView.findViewById<TextView>(R.id.txtWeakness)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(weakness: String, c: Context) {
            try{
                val uri = "@drawable/$weakness"
                val imageRes = itemView.resources.getIdentifier(uri, null, c.packageName)
                val res: Drawable = itemView.resources.getDrawable(imageRes)
                imgWeakness.setImageDrawable(res)
            }catch (err: Error){
                Log.d("QUE QUE ISSO", err.toString())
            }
            txtWeakness.text = PokeUtils.capitalize(weakness)
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