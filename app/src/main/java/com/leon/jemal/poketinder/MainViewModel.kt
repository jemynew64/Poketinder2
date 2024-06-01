package com.leon.jemal.poketinder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel() {

    val pokemonList = MutableLiveData<List<PokemonResponse>>()

    val isLoading = MutableLiveData<Boolean>()

    val errorApi = MutableLiveData<String>()

    //cuando se instancia quiero que me traiga los pokemones
    init {
        getAllPokemons()
    }

    private fun getAllPokemons() {
        //actualiza tu datos en true
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getRetrofit().create(PokemonApi::class.java).getPokemons()
                if(call.isSuccessful) {
                    call.body()?.let {
                        isLoading.postValue(false)
                        pokemonList.postValue(it.results)
                    }
                }
            } catch (e: Exception) {
                errorApi.postValue(e.message)
                isLoading.postValue(false)
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
