package com.goiz.pokedex


import com.goiz.pokedex.model.evolution_chain.EvolutionChain
import com.goiz.pokedex.view.PokemonActivity
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
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
        mActivity.addChain(evolutionChain.chain, evolutions)
        assertEquals(arrayListOf(2, 3), evolutions)

    }

    private fun getJson(): String {
        return File("./src/main/assets/chain.json").readText(Charsets.UTF_8)
    }

}