package com.leon.jemal.poketinder.data.network

import com.leon.jemal.poketinder.data.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET

// Interfaz que define los métodos de la API de Pokemon usando Retrofit
interface PokemonApi {
    // Define una llamada GET a la URL "/api/v2/pokemon"
    @GET("/api/v2/pokemon")
    suspend fun getPokemons(): Response<PokemonListResponse>
}