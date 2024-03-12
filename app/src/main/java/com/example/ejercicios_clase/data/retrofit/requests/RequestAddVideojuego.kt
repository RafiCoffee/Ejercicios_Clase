package com.example.ejercicios_clase.data.retrofit.requests

data class RequestAddVideojuego (
    val titulo: String,
    val genero: String?,
    val nota: Int?,
    val fechaSalida: String?,
    val imagen: String?
)