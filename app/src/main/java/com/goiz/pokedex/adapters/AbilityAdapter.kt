package com.goiz.pokedex.adapters

import android.content.Context
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.goiz.pokedex.R
import com.goiz.pokedex.utils.PokeUtils
import me.sargunvohra.lib.pokekotlin.model.Ability


class AbilityAdapter(
        private val abilityList: List<Ability>,
        private val context: Context
) : RecyclerView.Adapter<AbilityAdapter.AbilityViewHolder>() {
    class AbilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val txtTitle = itemView.findViewById<TextView>(R.id.txtAbilityTitle)
        private val txtDescription = itemView.findViewById<TextView>(R.id.txtAbilityDescription)

        fun bind(ability: Ability) {
            txtTitle.text = PokeUtils.capitalize(ability.name)

            for(effect in ability.effectEntries){
                if(effect.language.name == "en"){
                    txtDescription.text = effect.effect
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.abilities_item, parent, false)

        return AbilityViewHolder(view)
    }

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        holder.bind(abilityList[position])
    }

    override fun getItemCount(): Int {
        return abilityList.size
    }
}