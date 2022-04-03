package fr.o80.pokedex.lib.data

interface PokedexRepository {
    fun getPokedex(): Set<Int>
    fun addPokemon(id: Int)
    fun removePokemon(id: Int)
}