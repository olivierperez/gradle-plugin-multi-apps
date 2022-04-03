package fr.o80.pokedex.lib.feature.list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import fr.o80.pokedex.lib.R
import fr.o80.pokedex.lib.data.model.Pokemon

class PokemonAdapter(
    private val onPokemonClicked: (Pokemon) -> Unit,
    private val onPokeballClicked: (Pokemon) -> Unit,
) : ListAdapter<Pokemon, PokemonViewHolder>(PokemonDiff()) {

    private var pokedex: Set<Int> = emptySet()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pokemon, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(
            pokemon = pokemon,
            inPokedex = pokemon.id in pokedex,
            onPokemonClicked = { onPokemonClicked(pokemon) },
            onPokeballClicked = { onPokeballClicked(pokemon) },
        )
    }

    fun updatePokedex(pokedex: Set<Int>) {
        this.pokedex = pokedex
    }
}
