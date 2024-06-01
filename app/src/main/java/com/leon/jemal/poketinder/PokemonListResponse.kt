package com.leon.jemal.poketinder

//mapeas tu respuesta json a objeto o serializer
data class PokemonListResponse(
    val name: String,
    val url: String,
    val results: List<PokemonResponse>
)



