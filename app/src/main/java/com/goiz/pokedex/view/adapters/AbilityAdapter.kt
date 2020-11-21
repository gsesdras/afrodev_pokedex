package com.goiz.pokedex.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.goiz.pokedex.R
import com.goiz.pokedex.util.PokeUtils


class AbilityAdapter(
    private val abilityList: List<com.goiz.pokedex.model.ability.Ability>,
    private val context: Context
) : RecyclerView.Adapter<AbilityAdapter.AbilityViewHolder>() {
    class AbilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle = itemView.findViewById<TextView>(R.id.txtAbilityTitle)
        private val txtDescription = itemView.findViewById<TextView>(R.id.txtAbilityDescription)

        fun bind(ability: com.goiz.pokedex.model.ability.Ability) {
            txtTitle.text = PokeUtils.capitalize(ability.name)

            for (effect in ability.effect_entries) {
                if (effect.language.name == "en") {
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