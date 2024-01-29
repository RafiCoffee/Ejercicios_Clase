package com.example.ejercicios_clase.data.dialoges

interface DialogCallback{
    fun onDialogResult(newVideojuego: Array<String>, isCanceled: Boolean)
}