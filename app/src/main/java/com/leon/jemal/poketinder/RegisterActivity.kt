package com.leon.jemal.poketinder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.leon.jemal.poketinder.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerViewModel = RegisterViewModel(this)
        observerValues()
    }

    private fun observerValues(){
        registerViewModel.inputsError.observe(this) {
            Toast.makeText(this, "El email tiene que estar bien escrito y asegúrese de que las contraseñas coincidan ", Toast.LENGTH_SHORT).show()
        }

        registerViewModel.registerSuccess.observe(this) {
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            // Navega a la actividad de inicio de sesión o pantalla principal
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        // Configura el click del botón de registro
        binding.btnRegister.setOnClickListener {
            registerViewModel.registerInputs(
                email = binding.edtEmail.text.toString(),
                password = binding.edtPassword.text.toString(),
                password2 = binding.edtPassword2.text.toString()
            )
        }

        // Configura el click del botón de regresar
        binding.btnBackClose.setOnClickListener {
            finish() // Cierra la actividad actual
        }
    }
}