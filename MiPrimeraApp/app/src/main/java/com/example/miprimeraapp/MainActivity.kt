package com.example.miprimeraapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

// --- CLASE 5: CONCEPTOS DE POO (MOLDES DE DATOS) ---

// Clase Padre: Aplicamos Abstracción y Herencia (open)
open class Componente(
    val marca: String,
    protected val precioCompra: Double, // Encapsulamiento: Solo hijos lo ven
    val precioVenta: Double
) {
    open fun mostrarEnConsola() {
        Log.e("POO", "Componente Genérico - Marca: $marca")
    }
}

// Clase Hija: Pantalla (Hereda de Componente)
class Pantalla(val id: Int, val pulgadas: Double, marca: String, pCompra: Double, pVenta: Double)
    : Componente(marca, pCompra, pVenta) {

    override fun mostrarEnConsola() { // Polimorfismo: Cambiamos el comportamiento
        Log.e("POO", "PANTALLA: $marca de $pulgadas pulg. (ID: $id)")
    }
}

// Clase Hija: Teclado (Hereda de Componente)
class Teclado(val tipo: String, marca: String, pCompra: Double, pVenta: Double)
    : Componente(marca, pCompra, pVenta) {

    override fun mostrarEnConsola() {
        Log.e("POO", "TECLADO: $marca, Tipo: $tipo")
    }
}

// --- CLASE PRINCIPAL ---

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Referencias de la Interfaz
        val tilEmail = findViewById<TextInputLayout>(R.id.tilEmail)
        val tilPassword = findViewById<TextInputLayout>(R.id.tilPassword)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)

        // 2. Ejemplo de uso de POO (Clase 5)
        // Creamos objetos para probar que funcionan
        val miMonitor = Pantalla(1, 27.0, "LG", 150.0, 200.0)
        val miTeclado = Teclado("Mecánico", "Redragon", 30.0, 50.0)

        miMonitor.mostrarEnConsola()
        miTeclado.mostrarEnConsola()

        // 3. Lógica del botón (Navegación Clase 4)
        btnIngresar.setOnClickListener {
            val email = tilEmail.editText?.text.toString()
            val pass = tilPassword.editText?.text.toString()

            if (email.isNotEmpty() && pass.length >= 3) {
                // Navegar a la pantalla de Home
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)

                Toast.makeText(this, "Bienvenido $email", Toast.LENGTH_SHORT).show()
            } else {
                // Mostrar errores visuales en los campos
                if (email.isEmpty()) tilEmail.error = "Ingresa tu correo"
                if (pass.length < 3) tilPassword.error = "Contraseña muy corta"

                Toast.makeText(this, "Por favor, revisa los datos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
