package com.example.miprimeraapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {

    // 1. Lista privada y pública (Livedata de la Clase 9)
    private val _notesList = MutableLiveData<MutableList<NoteModel>>(mutableListOf())
    val notesList: LiveData<MutableList<NoteModel>> get() = _notesList

    // 2. FUNCIÓN DE LA CLASE 10: Agregar nota de forma asíncrona
    fun addNote(note: NoteModel) {
        // Lanzamos una corrutina en el scope del ViewModel
        // Esto se cancelará automáticamente si la pantalla se cierra
        viewModelScope.launch {

            // Simulamos que estamos guardando en una base de datos (Clase 11)
            // Esto "suspende" la función por 1 segundo sin congelar el celular
            delay(1000)

            val currentList = _notesList.value ?: mutableListOf()
            currentList.add(note)

            // Notificamos el cambio a la vista (MainActivity)
            // Al estar en el Main Thread por defecto, usamos .value
            _notesList.value = currentList
        }
    }
}