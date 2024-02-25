package com.example.ejercicios_clase.domain.userCase

import android.util.Log
import com.example.ejercicios_clase.data.dataSource.mem.models.RepositorioVideojuego
import com.example.ejercicios_clase.data.dataSource.mem.models.Videojuego

class GetVideojuegosUserCase {
    private var repositorioVideojuegos = RepositorioVideojuego()

    operator fun invoke() : List<Videojuego>{
        Log.i("TAG-VIDEOJUEGO", "Entra")
        return repositorioVideojuegos.getVideojuegos()
    }
}