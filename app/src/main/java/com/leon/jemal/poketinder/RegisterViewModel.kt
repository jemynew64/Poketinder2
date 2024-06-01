package com.leon.jemal.poketinder

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel (
    val context: Context
):ViewModel(){
    // Instancia de SharedPreferencesRepository para manejar SharedPreferences
    private val sharedPreferencesRepository = SharedPreferencesRepository().also {
        // Inicializa SharedPreferences con el contexto proporcionado
        it.setSharedPreference(context)
    }

    // LiveData para observar errores en los inputs (email y contrast vacuous)
    //si esta vacio o algo esta mal o no estan iguales las contraseñas
    val inputsError = MutableLiveData<Boolean>()
    val registerSuccess = MutableLiveData<Boolean>()

    fun registerInputs(email:String, password :String, password2: String){
        // Verifica si los inputs están vacíos
        if (isEmptyInputs(email, password, password2)|| !isEqual(password, password2)|| !isValidEmail(email) ) {
            inputsError.postValue(true)
            return
        }
        sharedPreferencesRepository.saveUserEmail(email)
        sharedPreferencesRepository.saveUserPassword(password)
        registerSuccess.postValue(true)
    }
    fun isEqual(password: String,password2: String) = password == password2
    fun isEmptyInputs(email: String, password: String, password2: String) = email.isEmpty() || password.isEmpty()|| password2.isEmpty()
    private fun isValidEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}