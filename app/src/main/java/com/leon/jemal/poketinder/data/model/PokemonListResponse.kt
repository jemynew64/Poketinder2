package com.leon.jemal.poketinder.data.model

//mapeas tu respuesta json a objeto o serializer
data class PokemonListResponse(
    val name: String,
    val url: String,
    val results: List<PokemonResponse>
)



