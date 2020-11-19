package com.goiz.pokedex

import junit.framework.Assert.assertEquals
import me.sargunvohra.lib.pokekotlin.model.ChainLink
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource
import org.junit.Test

class PokemonActivityTest {
    private val evolutions = arrayListOf<Int>()
    @Test
    fun chain_list_is_working(){
        //GIVEN
        val chainLink3 = ChainLink(
            false,
            NamedApiResource("Venusaur", "Grass", 3),
            listOf(),
            listOf()
        )
        val chainLink2 = ChainLink(
            false,
            NamedApiResource("Ivysaur", "Grass", 2),
            listOf(),
            listOf(chainLink3)
        )
        val chainLink1 = ChainLink(
            false,
            NamedApiResource("Bulbasaur", "Grass", 1),
            listOf(),
            listOf(chainLink2)
        )

        val chainList = listOf(chainLink1)

        //EXECUTION
        val mActivity = PokemonActivity()
        mActivity.addChain(chainList, evolutions)
        assertEquals(arrayListOf(1,2,3), evolutions)
    }
}