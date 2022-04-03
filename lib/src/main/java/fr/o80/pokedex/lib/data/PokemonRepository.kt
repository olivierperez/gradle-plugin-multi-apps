package fr.o80.pokedex.lib.data

import fr.o80.pokedex.lib.data.model.Pokemon

interface PokemonRepository {
    fun getPokemons(): List<Pokemon>
}
