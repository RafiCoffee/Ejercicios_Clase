package com.example.ejercicios_clase.domain.userCase

import com.example.ejercicios_clase.data.models.RepositorioVideojuegoDao

class EliminarVideojuegoUserCase {
    private var repositorioVideojuegos = RepositorioVideojuegoDao()

    operator fun invoke(pos: Int){
        repositorioVideojuegos.eliminarVideojuego(pos)
    }
}