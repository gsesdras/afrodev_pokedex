package com.goiz.pokedex

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.goiz.pokedex.adapters.PokemonAdapter
import com.goiz.pokedex.adapters.WeaknessesAdapter
import com.goiz.pokedex.utils.PokeUtils
import me.sargunvohra.lib.pokekotlin.model.Pokemon

class Stats() : Fragment() {
    private lateinit var txtHpValue: TextView;
    private lateinit var txtAtkValue: TextView;
    private lateinit var txtDefValue: TextView;
    private lateinit var txtSatkValue: TextView;
    private lateinit var txtSdefValue: TextView;
    private lateinit var txtSpdValue: TextView;
    private lateinit var progressBarHp: ProgressBar;
    private lateinit var progressBarAtk: ProgressBar;
    private lateinit var progressBarDef: ProgressBar;
    private lateinit var progressBarSatk: ProgressBar;
    private lateinit var progressBarSdef: ProgressBar;
    private lateinit var progressBarSpd: ProgressBar;
    private lateinit var rcWeaknesses: RecyclerView;


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view: View =  inflater.inflate(R.layout.fragment_stats, container, false)
        val pokemonStats = arguments?.getSerializable("PokemonStats") as HashMap<*, *>
        val weaknessesList = arguments?.getStringArrayList("Weaknesses")
        val resistancesList = arguments?.getStringArrayList("Resistances")

        val hp = pokemonStats["HP"] as Int
        val atk = pokemonStats["ATK"] as Int
        val def = pokemonStats["DEF"] as Int
        val satk = pokemonStats["SATK"] as Int
        val sdef = pokemonStats["SDEF"] as Int
        val spd = pokemonStats["SPD"] as Int


        txtHpValue = view.findViewById(R.id.txtHpValue)
        progressBarHp = view.findViewById(R.id.progressBarHp)
        txtHpValue.text = PokeUtils.idStatMask(hp)
        progressBarHp.setProgress(hp, true)

        txtAtkValue = view.findViewById(R.id.txtAtkValue)
        progressBarAtk = view.findViewById(R.id.progressBarAtk)
        txtAtkValue.text = PokeUtils.idStatMask(atk)
        progressBarAtk.setProgress(atk, true)

        txtDefValue = view.findViewById(R.id.txtDefValue)
        progressBarDef = view.findViewById(R.id.progressBarDef)
        txtDefValue.text = PokeUtils.idStatMask(def)
        progressBarDef.setProgress(def, true)

        txtSatkValue = view.findViewById(R.id.txtSatkValue)
        progressBarSatk = view.findViewById(R.id.progressBarSatk)
        txtSatkValue.text = PokeUtils.idStatMask(satk)
        progressBarSatk.setProgress(satk, true)

        txtSdefValue = view.findViewById(R.id.txtSdefValue)
        progressBarSdef = view.findViewById(R.id.progressBarSdef)
        txtSdefValue.text = PokeUtils.idStatMask(sdef)
        progressBarSdef.setProgress(sdef, true)

        txtSpdValue = view.findViewById(R.id.txtSpdValue)
        progressBarSpd = view.findViewById(R.id.progressBarSpd)
        txtSpdValue.text = PokeUtils.idStatMask(spd)
        progressBarSpd.setProgress(spd, true)

        rcWeaknesses = view.findViewById(R.id.rcWeaknesses)
        rcWeaknesses.adapter = weaknessesList?.let { WeaknessesAdapter(it, requireContext()) }

        return view
    }
}