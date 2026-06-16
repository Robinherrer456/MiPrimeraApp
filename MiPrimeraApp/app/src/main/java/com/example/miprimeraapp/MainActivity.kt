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

    // 1. Lanzador actualizado para la Clase 8 (Recibe objetos)
    private val formLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {

            // --- CAMBIO CLAVE CLASE 8 ---
            // Extraemos el objeto completo usando getParcelableExtra
            val note = result.data?.getParcelableExtra<NoteModel>("EXTRA_NOTE")

            if (note != null) {
                // Ahora usamos las propiedades del objeto (note.title)
                Toast.makeText(this, "Nota Guardada: ${note.title}", Toast.LENGTH_LONG).show()

                // Actualizamos la UI con los datos del objeto
                binding.textView.text = "Última nota: ${note.title}\nContenido: ${note.content}"

                // Simulamos que ahora sí hay contenido para mostrar
                canShowList = true
                updateVisibility()
            }
            // ----------------------------
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateVisibility()

        // 2. Al hacer clic en INGRESAR, vamos al formulario
        binding.btnIngresar.setOnClickListener {
            val intent = Intent(this, FormNoteActivity::class.java)
            formLauncher.launch(intent)
        }
    }

    private fun updateVisibility() {
        if (canShowList) {
            // Mostramos los campos si ya recibimos una nota
            binding.tilEmail.visibility = View.VISIBLE
            binding.tilPassword.visibility = View.VISIBLE
        } else {
            binding.textView.text = "Presiona INGRESAR para crear una nota"
            binding.tilEmail.visibility = View.GONE
            binding.tilPassword.visibility = View.GONE
        }
    }
}