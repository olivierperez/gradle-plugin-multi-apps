package fr.o80.pokedex.lib.feature.list.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.o80.pokedex.lib.R
import fr.o80.pokedex.lib.data.model.Pokemon

class PokemonViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    private val name: TextView = view.findViewById(R.id.nameTextView)
    private val hp: TextView = view.findViewById(R.id.hpTextView)
    private val attack: TextView = view.findViewById(R.id.attackTextView)
    private val defense: TextView = view.findViewById(R.id.defenseTextView)
    private val pokeball: ImageView = view.findViewById(R.id.pokeballImageView)

    fun bind(
        pokemon: Pokemon,
        inPokedex: Boolean,
        onPokemonClicked: () -> Unit,
        onPokeballClicked: () -> Unit
    ) {
        name.text = view.context.getString(R.string.pokemon_name_display, pokemon.name, pokemon.id)
        hp.text = pokemon.base.hp.toString()
        attack.text = pokemon.base.attack.toString()
        defense.text = pokemon.base.defense.toString()

        pokeball.apply {
            if (inPokedex) {
                contentDescription = "Pokemon is in pokedex"
                setImageResource(R.drawable.ic_pokeball_caught)
            } else {
                contentDescription = "Pokemon not yet caught"
                setImageResource(R.drawable.ic_pokeball_empty)
            }

            setOnClickListener {
                onPokeballClicked()
            }
        }

        view.setOnClickListener {
            onPokemonClicked()
        }
    }
}
