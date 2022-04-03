package fr.o80.pokedex.lib.domain

import dagger.hilt.android.scopes.ViewModelScoped
import fr.o80.pokedex.lib.data.PokemonRepository
import fr.o80.pokedex.lib.data.model.Pokemon
import javax.inject.Inject

@ViewModelScoped
class GetPokemonsUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(): List<Pokemon> {
        return repository.getPokemons()
    }
}
