package fr.o80.pokedex.lib.domain

import dagger.hilt.android.scopes.ViewModelScoped
import fr.o80.pokedex.lib.data.PokedexRepository
import javax.inject.Inject

@ViewModelScoped
class GetPokedexUseCase @Inject constructor(
    private val pokedexRepository: PokedexRepository
) {
    operator fun invoke(): Set<Int> {
        return pokedexRepository.getPokedex()
    }
}
