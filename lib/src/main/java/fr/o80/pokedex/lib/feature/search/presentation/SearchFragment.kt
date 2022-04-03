package fr.o80.pokedex.lib.feature.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import fr.o80.pokedex.lib.R
import fr.o80.pokedex.lib.feature.details.presentation.DetailsFragment
import fr.o80.pokedex.lib.feature.list.presentation.PokemonAdapter
import fr.o80.pokedex.lib.feature.search.viewmodel.SearchViewModel
import fr.o80.pokedex.lib.feature.search.viewmodel.SearchViewModel.Event.GoToDetails
import fr.o80.pokedex.lib.util.closeKeyboard

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val adapter: PokemonAdapter = PokemonAdapter(
        onPokemonClicked = { pokemon -> viewModel.onPokemonClicked(pokemon) },
        onPokeballClicked = { pokemon -> viewModel.onPokeballClicked(pokemon) }
    )

    private var viewHolder: ViewHolder? = null

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false).also {
            viewHolder = ViewHolder(it).apply {
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                searchField.setOnEditorActionListener { _, _, _ ->
                    search()
                    true
                }
                searchButton.setOnClickListener {
                    search()
                }
            }
        }
    }

    private fun ViewHolder.search() {
        viewModel.search(searchField.text.toString())
        closeKeyboard()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.pokemons.observe(viewLifecycleOwner) { (pokemons, pokedex) ->
            adapter.updatePokedex(pokedex)
            adapter.submitList(pokemons)
        }
        viewModel.events.observe(viewLifecycleOwner) { event ->
            when (event) {
                is GoToDetails -> {
                    parentFragmentManager.commit {
                        replace(R.id.main_content, DetailsFragment.newInstance(event.pokemon.id))
                        addToBackStack(null)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        viewHolder = null
        super.onDestroyView()
    }

    inner class ViewHolder(view: View) {
        val searchField: EditText = view.findViewById(R.id.search_field)
        val searchButton: ImageButton = view.findViewById(R.id.search_button)
        val recyclerView: RecyclerView = view.findViewById(R.id.pokemons_list)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}
