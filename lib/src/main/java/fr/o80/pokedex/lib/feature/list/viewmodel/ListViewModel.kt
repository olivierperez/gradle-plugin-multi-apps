package fr.o80.pokedex.lib.feature.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.o80.pokedex.lib.data.model.Pokemon
import fr.o80.pokedex.lib.domain.CatchPokemonUseCase
import fr.o80.pokedex.lib.domain.FreePokemonUseCase
import fr.o80.pokedex.lib.domain.GetPokedexUseCase
import fr.o80.pokedex.lib.domain.GetPokemonsUseCase
import fr.o80.pokedex.lib.util.EventLiveData
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getPokemons: GetPokemonsUseCase,
    private val getPokedex: GetPokedexUseCase,
    private val catchPokemon: CatchPokemonUseCase,
    private val freePokemon: FreePokemonUseCase
) : ViewModel() {

    private val _pokemons = MutableLiveData<Pair<List<Pokemon>, Set<Int>>>()
    val pokemons: LiveData<Pair<List<Pokemon>, Set<Int>>>
        get() = _pokemons

    private val _events = EventLiveData<Event>()
    val events: LiveData<Event>
        get() = _events

    init {
        _pokemons.postValue(Pair(getPokemons(), getPokedex()))
    }

    fun onPokeballClicked(pokemon: Pokemon) {
        if (getPokedex().contains(pokemon.id)) {
            freePokemon(pokemon)
        } else {
            catchPokemon(pokemon)
        }

        _pokemons.postValue(Pair(getPokemons(), getPokedex()))
    }

    fun onPokemonClicked(pokemon: Pokemon) {
        _events.postValue(Event.GoToDetails(pokemon))
    }

    sealed class Event {
        class GoToDetails(val pokemon: Pokemon) : Event()
    }
}
