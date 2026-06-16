package com.example.miprimeraapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.miprimeraapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // INSTANCIA DEL VIEWMODEL (Sobrevive a rotaciones)
    private val viewModel: NoteViewModel by viewModels()

    private val formLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val note = result.data?.getParcelableExtra<NoteModel>("EXTRA_NOTE")
            if (note != null) {
                // AGREGAMOS AL VIEWMODEL, NO A UNA LISTA LOCAL
                viewModel.addNote(note)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        // OBSERVAMOS EL LIVEDATA: Cuando la lista cambie, esto se ejecuta solo
        viewModel.notesList.observe(this) { notas ->
            if (notas.isNotEmpty()) {
                val ultimaNota = notas.last()
                binding.textView.text = "Notas totales: ${notas.size}\nÚltima: ${ultimaNota.title}"
                binding.tilEmail.visibility = View.VISIBLE
            } else {
                binding.textView.text = "No hay notas aún"
                binding.tilEmail.visibility = View.GONE
            }
        }
    }

    private fun setupListeners() {
        binding.btnIngresar.setOnClickListener {
            val intent = Intent(this, FormNoteActivity::class.java)
            formLauncher.launch(intent)
        }
    }
}