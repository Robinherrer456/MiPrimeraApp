package com.example.miprimeraapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.miprimeraapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var canShowList: Boolean = false

    // 1. Definimos el lanzador para recibir los datos de la Clase 7
    private val formLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // Extraemos los datos que enviamos desde FormNoteActivity
            val title = result.data?.getStringExtra("RESULT_TITLE")
            val content = result.data?.getStringExtra("RESULT_CONTENT")

            // Mostramos un mensaje con la nota recibida
            Toast.makeText(this, "Nota Recibida: $title", Toast.LENGTH_LONG).show()

            // Cambiamos el texto de tu pantalla principal
            binding.textView.text = "Última nota: $title"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateVisibility()

        // 2. Configuramos el botón para abrir el formulario de la Clase 7
        binding.btnIngresar.setOnClickListener {
            // Creamos el Intent para ir a la nueva actividad
            val intent = Intent(this, FormNoteActivity::class.java)

            // Si quisiéramos enviarle datos previos (ejemplo para editar):
            // intent.putExtra("EXTRA_TITLE", "Ejemplo")

            // Lanzamos la actividad esperando un resultado
            formLauncher.launch(intent)
        }
    }

    private fun updateVisibility() {
        if (canShowList) {
            binding.tilEmail.visibility = View.VISIBLE
            binding.textView.text = "¡Tienes notas nuevas!"
        } else {
            binding.textView.text = "Presiona INGRESAR para crear una nota"
        }
    }
}