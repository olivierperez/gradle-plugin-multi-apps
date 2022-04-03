package fr.o80.pokedex.lib.feature.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.o80.pokedex.lib.data.model.Pokemon
import fr.o80.pokedex.lib.domain.CatchPokemonUseCase
import fr.o80.pokedex.lib.domain.FreePokemonUseCase
import fr.o80.pokedex.lib.domain.GetPokedexUseCase
import fr.o80.pokedex.lib.domain.GetPokemonsUseCase
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPokemons: GetPokemonsUseCase,
    private val getPokedex: GetPokedexUseCase,
    private val catchPokemon: CatchPokemonUseCase,
    private val freePokemon: FreePokemonUseCase
) : ViewModel() {

    private val _pokemon = MutableLiveData<Pair<Pokemon, Boolean>>()
    val pokemon: LiveData<Pair<Pokemon, Boolean>>
        get() = _pokemon

    fun init(pokemonId: Int) {
        val pokemon = getPokemons().first { it.id == pokemonId }
        val isInPokedex = getPokedex().contains(pokemonId)
        _pokemon.postValue(Pair(pokemon, isInPokedex))
    }

    fun onPokeballClicked(shouldFreePokemon: Boolean) {
        val (pokemon, _) = pokemon.value!!
        val togglePokemon =
            if (shouldFreePokemon) freePokemon::invoke
            else catchPokemon::invoke
        togglePokemon(pokemon)
        _pokemon.postValue(Pair(pokemon, !shouldFreePokemon))
    }
}
