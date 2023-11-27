package com.example.ejercicios_clase.interfaces

import com.example.ejercicios_clase.models.Hotel

interface InterfaceDao {
    fun getDataHotels(): List<Hotel>
}