package com.goiz.pokedex.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goiz.pokedex.R
import com.goiz.pokedex.data.PokeService
import com.goiz.pokedex.data.pokemon.Pokemon
import com.goiz.pokedex.view.adapters.PokemonAdapter
import com.goiz.pokedex.viewmodel.ListPageViewModel
import pl.droidsonroids.gif.GifImageView

class HomePageList : AppCompatActivity(), PokemonAdapter.CellClickListener {

    private val title by lazy { findViewById<TextView>(R.id.title) }
    private val searchView by lazy { findViewById<SearchView>(R.id.searchView) }
    private val loading by lazy { findViewById<GifImageView>(R.id.loading) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    private val mAdapter by lazy { PokemonAdapter(this, this) }

    private val viewModel: ListPageViewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            ListPageViewModel.MainViewModelFactory(PokeService())
        ).get(ListPageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_list)
        val username = intent.getStringExtra("Username")
        val newTitle = "Oi, $username"
        title.text = newTitle

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@HomePageList)
            adapter = mAdapter
        }

        getPokemons()
        observe()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let {
                    handleSearch(it)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }

    private fun getPokemons() {
        viewModel.getPokemons()
        observe()
    }

    private fun getPokemon(name: String) {
        viewModel.getPokemon(name)
        observe()
    }

    private fun observe() {
        loading.visibility = View.VISIBLE
        viewModel.pokemonsData.observe(this, { list ->
            mAdapter.updateList(list)
            loading.visibility = View.GONE
        })
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

    private fun handleSearch(query: String) {
        when (query) {
            "pokemons", "all", "todos", " " -> {
                getPokemons()
            }
            else -> {
                getPokemon(query)
            }

        }
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
