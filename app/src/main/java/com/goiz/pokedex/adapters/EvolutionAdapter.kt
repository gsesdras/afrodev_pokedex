package com.goiz.pokedex.adapters

import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.goiz.pokedex.R
import com.goiz.pokedex.utils.PokeUtils
import com.squareup.picasso.Picasso


class EvolutionAdapter(
        private val evolutionList: MutableList<List<String>>,
        private val context: Context
) : RecyclerView.Adapter<EvolutionAdapter.EvolutionViewHolder>() {
    class EvolutionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val clBackground = itemView.findViewById<ConstraintLayout>(R.id.clBackground)
        private val pokeFirst = itemView.findViewById<ImageView>(R.id.pokemon1)
        private val pokeSecond = itemView.findViewById<ImageView>(R.id.pokemon2)
        private val txtPokeFirst = itemView.findViewById<TextView>(R.id.txtPokemon1)
        private val txtPokeSecond = itemView.findViewById<TextView>(R.id.txtPokemon2)
        private val baseURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

        @Suppress("DEPRECATION")
        fun bind(evolution: List<String>, c: Context) {
            val firstImage = "$baseURL${evolution[0]}.png"
            val secondImage = "$baseURL${evolution[1]}.png"
            Picasso.get().load(firstImage).into(pokeFirst)
            Picasso.get().load(secondImage).into(pokeSecond)
            txtPokeFirst.text = PokeUtils.capitalize(evolution[2])
            txtPokeSecond.text = PokeUtils.capitalize(evolution[3])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvolutionViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.evolution_item, parent, false)

        return EvolutionViewHolder(view)
    }

    override fun onBindViewHolder(holder: EvolutionViewHolder, position: Int) {
        holder.bind(evolutionList[position], context)
    }

    override fun getItemCount(): Int {
        return evolutionList.size
    }
}