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

        // 1. Detectar si es edición o creación
        checkExtras()

        // 2. Configurar botones
        setupListeners()
    }

    private fun checkExtras() {
        val title = intent.getStringExtra("EXTRA_TITLE")
        val content = intent.getStringExtra("EXTRA_CONTENT")

        if (title != null && content != null) {
            binding.tvTitlePage.text = "Actualizar Nota"
            binding.etTitle.setText(title)
            binding.etContent.setText(content)
            binding.btnSave.text = "Actualizar"
        }
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            if (validateForm()) {
                val resultIntent = Intent().apply {
                    putExtra("RESULT_TITLE", binding.etTitle.text.toString())
                    putExtra("RESULT_CONTENT", binding.etContent.text.toString())
                }
                setResult(RESULT_OK, resultIntent)
                finish() // Cierra la pantalla y vuelve a la anterior
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