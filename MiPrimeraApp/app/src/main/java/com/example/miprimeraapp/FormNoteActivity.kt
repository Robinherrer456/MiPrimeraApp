package com.example.miprimeraapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.miprimeraapp.databinding.ActivityFormNoteBinding

class FormNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Detectar si es edición o creación (Ahora recibiendo un objeto)
        checkExtras()

        // 2. Configurar botones
        setupListeners()
    }

    private fun checkExtras() {
        // En la Clase 8, lo ideal es recibir el objeto completo si es edición
        val noteToEdit = intent.getParcelableExtra<NoteModel>("EXTRA_NOTE")

        if (noteToEdit != null) {
            binding.tvTitlePage.text = "Actualizar Nota"
            binding.etTitle.setText(noteToEdit.title)
            binding.etContent.setText(noteToEdit.content)
            binding.btnSave.text = "Actualizar"
        }
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            if (validateForm()) {

                // --- CAMBIO CLAVE CLASE 8 ---
                // 1. Creamos el objeto NoteModel con los datos de los campos
                val noteResult = NoteModel(
                    title = binding.etTitle.text.toString(),
                    content = binding.etContent.text.toString()
                )

                // 2. Lo enviamos de vuelta en el Intent
                val resultIntent = Intent().apply {
                    putExtra("EXTRA_NOTE", noteResult)
                }
                // ----------------------------

                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        if (binding.etTitle.text.isNullOrBlank()) {
            binding.tilTitle.error = "Campo requerido"
            isValid = false
        } else {
            binding.tilTitle.error = null
        }

        if (binding.etContent.text.isNullOrBlank()) {
            binding.tilContent.error = "Campo requerido"
            isValid = false
        } else {
            binding.tilContent.error = null
        }

        return isValid
    }
}