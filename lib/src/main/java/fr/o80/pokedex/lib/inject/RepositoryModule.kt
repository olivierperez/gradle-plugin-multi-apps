package fr.o80.pokedex.lib.inject

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import fr.o80.pokedex.lib.data.InMemoryPokedexRepository
import fr.o80.pokedex.lib.data.InMemoryPokemonRepository
import fr.o80.pokedex.lib.data.PokedexRepository
import fr.o80.pokedex.lib.data.PokemonRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindPokemonsRepository(impl: InMemoryPokemonRepository): PokemonRepository

    @Binds
    abstract fun bindPokedexRepository(impl: InMemoryPokedexRepository): PokedexRepository
}
