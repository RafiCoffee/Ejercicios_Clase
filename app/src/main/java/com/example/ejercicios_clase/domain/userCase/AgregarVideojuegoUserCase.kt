package com.example.ejercicios_clase.domain.userCase

import com.example.ejercicios_clase.data.dataSource.mem.models.RepositorioVideojuego
import com.example.ejercicios_clase.data.dataSource.mem.models.Videojuego

class AgregarVideojuegoUserCase {
    private var repositorioVideojuegos = RepositorioVideojuego()

    operator fun invoke(videojuego: Videojuego){
        repositorioVideojuegos.agregarVideojuego(videojuego)
    }
}