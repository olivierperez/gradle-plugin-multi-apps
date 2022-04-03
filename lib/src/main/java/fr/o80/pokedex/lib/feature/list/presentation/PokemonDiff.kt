package fr.o80.pokedex.lib.feature.list.presentation

import androidx.recyclerview.widget.DiffUtil
import fr.o80.pokedex.lib.data.model.Pokemon

class PokemonDiff : DiffUtil.ItemCallback<Pokemon>() {

    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return false
    }
}
