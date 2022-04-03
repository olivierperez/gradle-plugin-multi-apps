package fr.o80.pokedex.lib.feature.list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import fr.o80.pokedex.lib.R
import fr.o80.pokedex.lib.data.model.Pokemon
import fr.o80.pokedex.lib.feature.list.viewmodel.ListViewModel
import fr.o80.pokedex.lib.feature.list.viewmodel.ListViewModel.Event.GoToDetails

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val adapter: PokemonAdapter = PokemonAdapter(
        onPokemonClicked = { pokemon -> viewModel.onPokemonClicked(pokemon) },
        onPokeballClicked = { pokemon -> viewModel.onPokeballClicked(pokemon) }
    )

    private val viewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false).apply {
            findViewById<RecyclerView>(R.id.pokemons).apply {
                adapter = this@ListFragment.adapter
                layoutManager = LinearLayoutManager(context, VERTICAL, false)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.pokemons.observe(viewLifecycleOwner) { (pokemons, pokedex) ->
            adapter.updatePokedex(pokedex)
            adapter.submitList(pokemons)
        }
        viewModel.events.observe(viewLifecycleOwner) { event ->
            when (event) {
                is GoToDetails -> (activity as? PokemonsListListener)?.goToDetails(event.pokemon)
            }
        }
    }

    interface PokemonsListListener {
        fun goToDetails(pokemon: Pokemon)
    }

    companion object {
        fun newInstance() = ListFragment()
    }
}
