package com.example.ejercicios_clase.data.models

import android.util.Log
import com.example.ejercicios_clase.data.Estadisticas
import com.example.ejercicios_clase.data.service.VideojuegoService

class RepositorioVideojuegoDao : RepositorioVideojuegoInterfaceDao {
    private val service : VideojuegoService = VideojuegoService()
    override fun getVideojuegos(): List<Videojuego> {
        val dataSource = service.getVideojuegos()
        actualizarLista(dataSource)
        return Repositorio.videojuegos
    }

    override fun getVideojuegosNota(nota: Int): List<Videojuego> {
        val dataSource = service.getVideojuegosNota(nota)
        actualizarLista(dataSource)
        return Repositorio.videojuegos
    }

    override fun agregarVideojuego(videojuegoNuevo: Videojuego) {
        var listaVideojuegosMutable : MutableList<Videojuego> = mutableListOf()
        val dataSource = service.getVideojuegos()
        listaVideojuegosMutable = actualizarLista(dataSource)
        listaVideojuegosMutable.add(videojuegoNuevo)
        Repositorio.videojuegos = listaVideojuegosMutable
        Estadisticas.totalJuegos = Repositorio.videojuegos.size
        Estadisticas.totalJuegosAgregados++
    }

    override fun editarVideojuego(pos: Int, videojuegoEditado: Videojuego) {
        var listaVideojuegosMutable : MutableList<Videojuego> = mutableListOf()
        val dataSource = service.getVideojuegos()
        listaVideojuegosMutable = actualizarLista(dataSource)
        listaVideojuegosMutable.set(pos, videojuegoEditado)
        Repositorio.videojuegos = listaVideojuegosMutable
        Estadisticas.totalJuegos = Repositorio.videojuegos.size
        Estadisticas.totalJuegosEditados++
    }

    override fun eliminarVideojuego(pos: Int) {
        var listaVideojuegosMutable : MutableList<Videojuego> = mutableListOf()
        val dataSource = service.getVideojuegos()
        listaVideojuegosMutable = actualizarLista(dataSource)
        Log.i("PRUEBA", "Actualizar lista --- " + Repositorio.videojuegos.size)
        listaVideojuegosMutable.removeAt(pos)
        Repositorio.videojuegos = listaVideojuegosMutable
        Estadisticas.totalJuegos = Repositorio.videojuegos.size
        Estadisticas.totalJuegosEliminados++
        Log.i("PRUEBA", "Lista actualizada --- " + Repositorio.videojuegos.size)
    }

    private fun actualizarLista(dataSource: List<Videojuego>): MutableList<Videojuego> {
        val listaVideojuegosMutable: MutableList<Videojuego> = mutableListOf()
        dataSource.forEach { videojuego ->
            listaVideojuegosMutable.add(Videojuego(videojuego.titulo, videojuego.genero, videojuego.nota, videojuego.fechaSalida, videojuego.image))
        }
        Repositorio.videojuegos = listaVideojuegosMutable
        return listaVideojuegosMutable
    }
}