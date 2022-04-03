package fr.o80.pokedex.lib.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon(
    val id: Int,
    val name: String,
    val base: PokemonBase
)

@JsonClass(generateAdapter = true)
data class PokemonBase(
    @Json(name = "HP")
    val hp: Int,
    @Json(name = "Attack")
    val attack: Int,
    @Json(name = "Defense")
    val defense: Int,
)
