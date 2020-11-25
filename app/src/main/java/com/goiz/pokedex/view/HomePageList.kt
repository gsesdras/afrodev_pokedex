package com.goiz.pokedex.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goiz.pokedex.R
import com.goiz.pokedex.model.pokemon.Pokemon
import com.goiz.pokedex.res.respository.ListPageRepository
import com.goiz.pokedex.view.adapters.PokemonAdapter
import com.goiz.pokedex.viewmodel.ListPageViewModel
import pl.droidsonroids.gif.GifImageView
import java.util.*

class HomePageList : AppCompatActivity(), PokemonAdapter.CellClickListener {

    private val title by lazy { findViewById<TextView>(R.id.title) }
    private val searchView by lazy { findViewById<SearchView>(R.id.searchView) }
    private val loading by lazy { findViewById<GifImageView>(R.id.loading) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    private val viewModel = ViewModelProvider(
        viewModelStore,
        ListPageViewModel.MainViewModelFactory(ListPageRepository())
    ).get(ListPageViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_list)
        val username = intent.getStringExtra("Username")

        val newTitle = "Oi, $username"
        title.text = newTitle

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let{ getPokemonByName(p0)}
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        getPokemonList()
    }

    private fun getPokemonList() {
        loading.visibility = View.VISIBLE
        recyclerView.adapter = null
        viewModel.getEvolutionChainList().observe(this, { t ->
            val list = mutableListOf<Pokemon>()
            t.results.forEach { it ->
                viewModel.getEvolutionChain(it.url.substringAfterLast("evolution-chain/"))
                    .observe(this@HomePageList, { evolutionChain ->
                        viewModel.getPokemonByName(evolutionChain.chain.species.name)
                            .observe(this, { pokemon ->
                                list.add(pokemon)
                                setList(list)
                            })
                    })
            }
        })
    }

    private fun setList(list: MutableList<Pokemon>) {
        recyclerView.apply {
            list.sortBy { it.id }
            val mAdapter = PokemonAdapter(list, this@HomePageList, this.context)
            layoutManager = LinearLayoutManager(this.context)
            adapter = mAdapter
            loading.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    private fun getPokemonByName(name: String) {
        loading.visibility = View.VISIBLE
        recyclerView.adapter = null
        if (name == "pokemons") {
            getPokemonList()
        } else {
            viewModel.getPokemonByName(name).observe(this,
                { t ->
                    val list = mutableListOf<Pokemon>()
                    list.add(t)
                    setList(list)
                })
        }
    }

    fun handleGoToAboutActivity(view: View) {
        view.animate().apply {
            duration = 300
            translationYBy(-10f)
        }.withEndAction {
            view.animate().apply {
                duration = 300
                translationYBy(10f)
            }.withEndAction {
                startActivity(Intent(this, About::class.java))
            }
        }.start()
    }

    fun logOut(view: View) {
        view.animate().apply {
            duration = 300
            alpha(.8f)
        }
        val sharedPref =
            this.getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            clear()
            commit()
        }
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onBackPressed() {
        finishAffinity()
    }

    override fun onCellClickListener(data: Pokemon) {
        val intent = Intent(this, PokemonActivity::class.java).apply {
            putExtra("PokemonId", data.id.toString())
            putExtra("PokemonType", data.types[0].type.name)
        }
        startActivity(intent)
    }
}
