package com.example.ejercicios_clase.data.dataSource.mem.service

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicios_clase.data.dataSource.mem.models.Repositorio
import com.example.ejercicios_clase.data.dataSource.mem.models.Videojuego
import com.example.ejercicios_clase.data.retrofit.RetrofitModule

class VideojuegoService : VideojuegoServiceInterface {

    override fun getVideojuegos(): List<Videojuego> {
        return Repositorio.videojuegos
    }

    override fun getVideojuegosNota(nota: Int): List<Videojuego> {
        val videojuegosNota = Repositorio.videojuegos.filter {
            it.nota!!.equals(nota)
        }
        return videojuegosNota
    }
}