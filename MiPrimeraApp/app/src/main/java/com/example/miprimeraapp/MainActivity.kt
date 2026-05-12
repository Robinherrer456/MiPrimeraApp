package com.example.miprimeraapp // Revisa que este nombre sea el que te sugirió el banner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Referencias usando TUS IDs del XML
        val tilEmail = findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.tilEmail)
        val tilPassword = findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.tilPassword)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)

        // 2. Lógica del botón
        btnIngresar.setOnClickListener {
            val email = tilEmail.editText?.text.toString()
            val pass = tilPassword.editText?.text.toString()

            if (email.isNotEmpty() && pass.length >= 3) {
                // Ir a la siguiente pantalla
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Datos incompletos", Toast.LENGTH_SHORT).show()
            }
        }
    }
    }