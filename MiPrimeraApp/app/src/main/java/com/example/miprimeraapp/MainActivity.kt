package com.example.miprimeraapp // Revisa que este nombre sea el que te sugirió el banner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Definimos las variables
        val etEmail = findViewById<EditText>(R.id.etUsername)
        val etPass = findViewById<EditText>(R.id.etPassword)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)

        // 2. Acción del botón
        btnIngresar.setOnClickListener {
            val email = etEmail.text.toString()

            // Navegación
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

            Toast.makeText(this, "Bienvenido $email", Toast.LENGTH_SHORT).show()
        }
    }
}