package com.example.ejercicios_clase.dao

import com.example.ejercicios_clase.interfaces.InterfaceDao
import com.example.ejercicios_clase.models.Hotel
import com.example.ejercicios_clase.object_models.Repositorio

class DaoHotels private constructor() : InterfaceDao {
    companion object {
        val mydao: DaoHotels by lazy {
            DaoHotels()
        }
    }

    override fun getDataHotels(): List<Hotel> = Repositorio.listHotels
}