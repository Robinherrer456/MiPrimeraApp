package com.example.miprimeraapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteModel(
    val title: String,
    val content: String
) : Parcelable
