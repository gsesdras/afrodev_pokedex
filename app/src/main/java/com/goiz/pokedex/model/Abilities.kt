package com.goiz.pokedex.model

import com.goiz.pokedex.model.ability.Ability
import java.io.Serializable

class Abilities(
        val abilities: List<Ability>
) : Serializable