package fr.o80.pokedex.lib.data

import android.content.Context
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.qualifiers.ApplicationContext
import fr.o80.pokedex.lib.R
import fr.o80.pokedex.lib.data.model.Pokemon
import okio.buffer
import okio.source
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryPokemonRepository @Inject constructor(
    @ApplicationContext context: Context
) : PokemonRepository {

    private val pokemons: List<Pokemon>

    init {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter<List<Pokemon>>(
            Types.newParameterizedType(
                List::class.java,
                Pokemon::class.java
            )
        )
        context.resources.openRawResource(R.raw.pokemons).use { inputStream ->
            val reader = JsonReader.of(inputStream.source().buffer())
            pokemons = adapter.fromJson(reader)!!
        }
    }

    override fun getPokemons(): List<Pokemon> {
        return pokemons.toList()
    }
}
