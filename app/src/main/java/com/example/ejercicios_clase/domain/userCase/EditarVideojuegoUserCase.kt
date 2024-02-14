package com.example.ejercicios_clase.domain.userCase

import com.example.ejercicios_clase.data.models.RepositorioVideojuegoDao
import com.example.ejercicios_clase.data.models.Videojuego

class EditarVideojuegoUserCase {
    private var repositorioVideojuegos = RepositorioVideojuegoDao()

    operator fun invoke(pos: Int, videojuego: Videojuego){
        repositorioVideojuegos.editarVideojuego(pos, videojuego)
    }
}