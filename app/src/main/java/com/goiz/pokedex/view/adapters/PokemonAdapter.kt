package com.goiz.pokedex.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.goiz.pokedex.R
import com.goiz.pokedex.model.pokemon.Pokemon
import com.goiz.pokedex.util.PokeUtils
import com.squareup.picasso.Picasso


class PokemonAdapter(
    private val pokemonList: MutableList<Pokemon>,
    private val cellClickListener: CellClickListener,
    private val context: Context
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pokemonName: TextView = itemView.findViewById(R.id.pokemon_name)
        private val pokemonId: TextView = itemView.findViewById(R.id.pokemon_id)
        private val pokemonIcon: ImageView = itemView.findViewById(R.id.pokemon_icon)
        private val pokemonPrimaryType = itemView.findViewById<ImageView>(R.id.primary_type)


        fun bind(pokemonReference: Pokemon, context: Context) {
            val name = pokemonReference.name.let { PokeUtils.capitalize(it) }

            pokemonName.text = name
            pokemonId.text = pokemonReference.id.let { PokeUtils.idMask(it) }

            val imageURL =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemonReference.id}.png"
            Picasso.get().load(imageURL).into(pokemonIcon)

            pokemonPrimaryType.setImageDrawable(
                PokeUtils.getImageByString(
                    context,
                    pokemonReference.types[0].type.name
                )
            )
        }
    }


    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)

        return PokemonViewHolder(view)
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return pokemonList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position], context)
        val data = pokemonList[position]
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(data)
        }
    }

    interface CellClickListener {
        fun onCellClickListener(data: Pokemon)
    }
}
