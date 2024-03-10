package com.example.ejercicios_clase.domain.userCase

import com.example.ejercicios_clase.data.dataSource.mem.models.RepositorioVideojuego

class EliminarVideojuegoUserCase {
    private var repositorioVideojuegos = RepositorioVideojuego()

    operator fun invoke(pos: Int){
        repositorioVideojuegos.eliminarVideojuego(pos)
    }
}