package com.example.ejercicios_clase.domain.userCase

import com.example.ejercicios_clase.data.dataSource.mem.models.RepositorioVideojuego
import com.example.ejercicios_clase.data.dataSource.mem.models.Videojuego

class EditarVideojuegoUserCase {
    private var repositorioVideojuegos = RepositorioVideojuego()

    operator fun invoke(pos: Int, videojuego: Videojuego){
        repositorioVideojuegos.editarVideojuego(pos, videojuego)
    }
}