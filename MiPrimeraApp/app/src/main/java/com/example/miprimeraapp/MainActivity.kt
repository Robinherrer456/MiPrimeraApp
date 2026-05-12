package com.example.miprimeraapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.miprimeraapp.databinding.ActivityMainBinding
import java.util.Date

class MainActivity : AppCompatActivity() {

    // Configuramos View Binding (Clase 6)
    private lateinit var binding: ActivityMainBinding
    private var canShowList: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflamos la vista con Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Al iniciar, ocultamos la lista y mostramos el mensaje vacío
        updateVisibility()

        // Configuramos el botón de agregar (Floating Action Button)
        binding.btnIngresar.setOnClickListener {
            // Simulamos que al presionar el botón "aparecen" notas
            canShowList = !canShowList
            updateVisibility()
        }
    }

    private fun updateVisibility() {
        if (canShowList) {
            // Mostramos el RecyclerView y ocultamos el mensaje de "Atención"
            binding.tilEmail.visibility = View.VISIBLE // Aquí iría tu RecyclerView
            binding.textView.text = "¡Tienes notas nuevas!"
            // En la clase 6 el profesor oculta el Linear que dice "Atención"
        } else {
            // Ocultamos lista y mostramos mensaje vacío
            binding.textView.text = "No hay notas importantes"
        }
    }
}