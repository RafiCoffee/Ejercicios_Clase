package com.example.ejercicios_clase.data.retrofit.requests

data class RequestRegisterUsuario (
    val email: String,
    val password: String,
    val username: String,
    val imagen: String? = null,
    val disponible: Int = 1
)