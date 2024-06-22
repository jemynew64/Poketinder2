package com.leon.jemal.poketinder.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leon.jemal.poketinder.data.model.PokemonResponse
import com.leon.jemal.poketinder.data.network.PokemonApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// ViewModel que maneja los datos de la vista principal
class HomeViewModel: ViewModel() {

    // LiveData para la lista de Pokemones
    val pokemonList = MutableLiveData<List<PokemonResponse>>()

    // LiveData para indicar si se está cargando la información
    val isLoading = MutableLiveData<Boolean>()

    // LiveData para manejar errores de la API
    val errorApi = MutableLiveData<String>()

    // Inicializa el ViewModel y llama a getAllPokemons para cargar los datos
    init {
        getAllPokemons()
    }

    // Función que obtiene todos los Pokemones desde la API
    private fun getAllPokemons() {
        // Indica que se está cargando la información
        isLoading.postValue(true)
        // Inicia una corrutina en el dispatcher IO para operaciones de red
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Realiza la llamada a la API usando Retrofit
                val call = getRetrofit().create(PokemonApi::class.java).getPokemons()
                if (call.isSuccessful) {
                    call.body()?.let {
                        // Si la llamada es exitosa, actualiza la lista de Pokemones
                        isLoading.postValue(false)
                        pokemonList.postValue(it.results)
                    }
                }
            } catch (e: Exception) {
                // Maneja cualquier error que ocurra durante la llamada a la API
                errorApi.postValue(e.message)
                isLoading.postValue(false)
            }
        }
    }

    // Función que configura y devuelve una instancia de Retrofit
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}