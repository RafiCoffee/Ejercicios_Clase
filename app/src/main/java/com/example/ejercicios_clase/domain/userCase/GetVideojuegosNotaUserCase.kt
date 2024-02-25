package com.example.ejercicios_clase.domain.userCase

import com.example.ejercicios_clase.data.dataSource.mem.models.RepositorioVideojuego
import com.example.ejercicios_clase.data.dataSource.mem.models.Videojuego

class GetVideojuegosNotaUserCase(val nota : Int) {
    private var repositorioVideojuegos = RepositorioVideojuego()

    operator fun invoke() : List<Videojuego>?{
        return repositorioVideojuegos.getVideojuegosNota(nota)
    }
}