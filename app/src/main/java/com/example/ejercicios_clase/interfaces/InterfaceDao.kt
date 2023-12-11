package com.example.ejercicios_clase.interfaces

import com.example.ejercicios_clase.models.Hotel
import com.example.ejercicios_clase.object_models.Repositorio

interface InterfaceDao {
    fun getDataHotels(): List<Hotel> = Repositorio.listHotels
}