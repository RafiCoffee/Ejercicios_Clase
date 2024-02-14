package com.example.ejercicios_clase.domain.userCase

import com.example.ejercicios_clase.data.models.RepositorioVideojuegoDao
import com.example.ejercicios_clase.data.models.Videojuego

class GetVideojuegosNotaUserCase(val nota : Int) {
    private var repositorioVideojuegos = RepositorioVideojuegoDao()

    operator fun invoke() : List<Videojuego>?{
        return repositorioVideojuegos.getVideojuegosNota(nota)
    }
}