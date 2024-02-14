package com.example.ejercicios_clase.domain.userCase

import com.example.ejercicios_clase.data.models.RepositorioVideojuegoDao
import com.example.ejercicios_clase.data.models.Videojuego

class AgregarVideojuegoUserCase {
    private var repositorioVideojuegos = RepositorioVideojuegoDao()

    operator fun invoke(videojuego: Videojuego){
        repositorioVideojuegos.agregarVideojuego(videojuego)
    }
}