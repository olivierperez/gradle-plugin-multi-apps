package fr.o80.pokedex.lib.feature.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import fr.o80.pokedex.lib.R
import fr.o80.pokedex.lib.data.model.Pokemon

private const val ARG_POKEMON_ID = "ARG_POKEMON_ID"

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var pokemonId: Int = -1

    private var viewHolder: ViewHolder? = null

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pokemonId = it.getInt(ARG_POKEMON_ID)
        }

        viewModel.init(pokemonId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false).also {
            viewHolder = ViewHolder(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.pokemon.observe(viewLifecycleOwner) { (pokemon, isInPokedex) ->
            viewHolder?.bind(pokemon, isInPokedex)
        }
    }

    override fun onDestroyView() {
        viewHolder = null
        super.onDestroyView()
    }

    inner class ViewHolder(view: View) {
        private val name: TextView = view.findViewById(R.id.tv_name)
        private val pokeball: ImageView = view.findViewById(R.id.iv_pokeball)
        private val health: TextView = view.findViewById(R.id.tv_hp_value)
        private val force: TextView = view.findViewById(R.id.tv_attack_value)
        private val protection: TextView = view.findViewById(R.id.tv_defense_value)

        fun bind(pokemon: Pokemon, isInPokedex: Boolean) {
            name.text = pokemon.name
            health.text = pokemon.base.hp.toString()
            force.text = pokemon.base.attack.toString()
            protection.text = pokemon.base.defense.toString()

            pokeball.apply {
                if (isInPokedex) {
                    contentDescription = "Pokemon is in pokedex"
                    setImageResource(R.drawable.ic_pokeball_caught)
                } else {
                    contentDescription = "Pokemon not yet caught"
                    setImageResource(R.drawable.ic_pokeball_empty)
                }

                setOnClickListener {
                    viewModel.onPokeballClicked(isInPokedex)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(pokemonId: Int) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POKEMON_ID, pokemonId)
                }
            }
    }
}
