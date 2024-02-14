package com.example.ejercicios_clase.domain.userCase

import android.util.Log
import com.example.ejercicios_clase.data.models.RepositorioVideojuegoDao
import com.example.ejercicios_clase.data.models.Videojuego

class GetVideojuegosUserCase {
    private var repositorioVideojuegos = RepositorioVideojuegoDao()

    operator fun invoke() : List<Videojuego>{
        Log.i("TAG-VIDEOJUEGO", "Entra")
        return repositorioVideojuegos.getVideojuegos()
    }
}