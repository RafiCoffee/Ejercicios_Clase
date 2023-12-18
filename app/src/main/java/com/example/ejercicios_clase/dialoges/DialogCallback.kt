package com.example.ejercicios_clase.dialoges

interface DialogCallback{
    fun onDialogResult(newVideojuego: Array<String>, isCanceled: Boolean)
}