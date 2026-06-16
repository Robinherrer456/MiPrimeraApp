package com.example.miprimeraapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {

    // 1. Lista privada que se puede modificar (Mutable)
    private val _notesList = MutableLiveData<MutableList<NoteModel>>(mutableListOf())

    // 2. Lista pública que solo se puede observar (No mutable)
    val notesList: LiveData<MutableList<NoteModel>> get() = _notesList

    // 3. Función para agregar una nota y avisar a la vista
    fun addNote(note: NoteModel) {
        val currentList = _notesList.value ?: mutableListOf()
        currentList.add(note)

        // Notificamos el cambio (Esto dispara el observador en MainActivity)
        _notesList.value = currentList
    }
}
