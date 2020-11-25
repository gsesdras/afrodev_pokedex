package com.goiz.pokedex


import com.goiz.pokedex.model.evolution_chain.EvolutionChain
import com.goiz.pokedex.view.PokemonActivity
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class PokemonActivityTest {
    @Test
    fun chain_list_is_working() {
        val mActivity = PokemonActivity()
        //GIVEN
        val json = getJson()
        val gson = Gson()

        val evolutionChain: EvolutionChain = gson.fromJson(json, EvolutionChain::class.java)

        val evolutions = ArrayList<Int>()
        val evolutionsName = ArrayList<String>()

        mActivity.addChain(evolutionChain.chain, evolutions, evolutionsName)
        assertEquals(arrayListOf(1, 2, 3), evolutions)
        assertEquals(arrayListOf("bulbasaur", "ivysaur", "venusaur"), evolutionsName)

    }

    private fun getJson(): String {
        return File("./src/main/assets/chain.json").readText(Charsets.UTF_8)
    }

}