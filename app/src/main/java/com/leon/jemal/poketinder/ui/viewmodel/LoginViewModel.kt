package com.leon.jemal.poketinder.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leon.jemal.poketinder.data.database.SharedPreferencesRepository

// El ViewModel para gestionar la lógica de autenticación del usuario
class LoginViewModel(
    // Contexto de la aplicación o actividad
    val context: Context
) : ViewModel() {
    // Instancia de SharedPreferencesRepository para manejar SharedPreferences
    private val sharedPreferencesRepository = SharedPreferencesRepository().also {
        // Inicializa SharedPreferences con el contexto proporcionado
        it.setSharedPreference(context)
    }

    // LiveData para observar errores en los inputs (email y contrast vacuous)
    val inputsError = MutableLiveData<Boolean>()
    val loginSuccess = MutableLiveData<Boolean>()
    val authError = MutableLiveData<Boolean>()

    // Función para validar los inputs del usuario (email y contraseña)
    fun validateInputs(email: String, password: String) {
        if (isEmptyInputs(email, password)) {
            inputsError.postValue(true)
            return
        }

        // Recupera el email y la contraseña almacenados en SharedPreferences
        val emailSharedPreferences = sharedPreferencesRepository.getUserEmail()
        val passwordSharedPreferences = sharedPreferencesRepository.getUserPassword()

        // Compara los inputs del usuario con los valores almacenados
        if (email == emailSharedPreferences && password == passwordSharedPreferences) {
            loginSuccess.postValue(true)
        } else {
            authError.postValue(true)
        }
    }

    // Función para verificar si los inputs están vacíos
    // Buena práctica: el nombre de la función comienza con "is" para indicar que retorna un booleano
    fun isEmptyInputs(email: String, password: String) = email.isEmpty() || password.isEmpty()
}
