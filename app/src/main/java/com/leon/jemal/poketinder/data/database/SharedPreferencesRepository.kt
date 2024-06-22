package com.leon.jemal.poketinder.data.database

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesRepository {

    //el companion object es para que no sean editables es una seguridad extra para variables sensibles
    companion object {
        // Instancia de SharedPreferences que se usará en toda la clase
        private lateinit var sharedPreferences: SharedPreferences
        //para este tipo de variable snate keys se recomienda que sea una constante en se formato es por el formato
        //ese formato es para constante
        private const val SHARED_PREFERENCES_KEY = "SHARED_PREFERENCES_KEY"
        private const val USER_EMAIL_KEY = "USER_EMAIL_KEY"
        private const val USER_PASSWORD_KEY ="USER_PASSWORD_KEY"
    }
    //aca lo deja instanciado para poder usarlo porque pueden aver muchos y con la llave sabes que es diferente
    fun setSharedPreference(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
    }


    // Recupera el email del usuario desde SharedPreferences
    // Si el valor es nulo, se devuelve una cadena vacía
    fun getUserEmail(): String {
        return sharedPreferences.getString(USER_EMAIL_KEY, "") ?: ""
    }

    fun getUserPassword(): String {
        return sharedPreferences.getString(USER_PASSWORD_KEY, "") ?: ""
    }

    //aca guardo el emal del usuario
    fun saveUserEmail(email: String) {
        //cual de todos los shared
        sharedPreferences
            //editar que
            .edit()
            //aca altualiza o agrega en caso de que exista o no
            .putString(USER_EMAIL_KEY, email)
            //aca aplica el cambio de manera asincrona
            .apply()
    }

    fun saveUserPassword(password: String) {
        sharedPreferences
            .edit()
            .putString(USER_PASSWORD_KEY, password)
            .apply()
    }


}