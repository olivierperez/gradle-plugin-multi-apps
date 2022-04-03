package fr.o80.pokedex.lib.domain

import dagger.hilt.android.scopes.ViewModelScoped
import fr.o80.pokedex.lib.data.PokedexRepository
import fr.o80.pokedex.lib.data.model.Pokemon
import javax.inject.Inject

@ViewModelScoped
class CatchPokemonUseCase @Inject constructor(
    private val pokedexRepository: PokedexRepository
) {
    operator fun invoke(pokemon: Pokemon) {
        pokedexRepository.addPokemon(pokemon.id)
    }
}
