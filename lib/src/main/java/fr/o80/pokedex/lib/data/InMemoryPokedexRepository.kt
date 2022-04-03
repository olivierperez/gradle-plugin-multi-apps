package fr.o80.pokedex.lib.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryPokedexRepository @Inject constructor() : PokedexRepository {

    private var pokedex = mutableSetOf<Int>()

    override fun getPokedex(): Set<Int> {
        return pokedex
    }

    override fun addPokemon(id: Int) {
        pokedex.add(id)
    }

    override fun removePokemon(id: Int) {
        pokedex.remove(id)
    }
}