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
import com.squareup.picasso.Picasso
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*


class PokemonAdapter(
    private val pokemonList: List<NamedApiResource>,
    private val cellClickListener: CellClickListener,
    private val context: Context
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val pokemonName: TextView = itemView.findViewById(R.id.pokemon_name)
        private val pokemonId: TextView = itemView.findViewById(R.id.pokemon_id)
        private val pokemonIcon: ImageView = itemView.findViewById(R.id.pokemon_icon)
        private val pokemonPrimaryType = itemView.findViewById<ImageView>(R.id.primary_type)
        private val pokeUtils = PokeUtils()


        fun bind(pokemonReference: NamedApiResource, context: Context) {
            val name = pokemonReference.name.substring(0, 1).toUpperCase(Locale.ROOT) + (pokemonReference.name.substring(1));
            pokemonName.text = name
            pokemonId.text = pokeUtils.idMask(pokemonReference.id)

            val pokeApi = PokeApiClient()

            doAsync {
                pokemonPrimaryType.visibility = View.INVISIBLE
                val pokemon = pokeApi.getPokemon(pokemonReference.id)
                 uiThread {
                    pokemonPrimaryType.setImageDrawable(pokeUtils.getImageByString(context, pokemon.types[0].type.name))
                    pokemonPrimaryType.visibility = View.VISIBLE
                }
            }

            val imageURL =
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemonReference.id}.png"
            Picasso.get().load(imageURL).into(pokemonIcon)
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
        val data  = pokemonList[position]
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(data)
        }
    }

    interface CellClickListener {
        fun onCellClickListener(data: NamedApiResource)
    }
}
