package fr.o80.pokedex.lib.domain

import dagger.hilt.android.scopes.ViewModelScoped
import fr.o80.pokedex.lib.data.PokemonRepository
import fr.o80.pokedex.lib.data.model.Pokemon
import javax.inject.Inject

@ViewModelScoped
class SearchPokemonsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(search: String): List<Pokemon> {
        return pokemonRepository.getPokemons()
            .filter { pokemon -> pokemon.name.contains(search, ignoreCase = true) }
    }
}
