package com.example.ejercicios_clase.controller

import android.content.Context
import com.example.ejercicios_clase.MainActivity
import com.example.ejercicios_clase.dao.DaoHotels
import com.example.ejercicios_clase.models.Hotel

class Controller(val contexto: Context) {
    lateinit var listHotels: MutableList<Hotel>

    init {
        //initData()
    }

    fun initData(){
        //listHotels = DaoHotels.myDao.getDataHotels().toMutableList()
    }

    fun setAdapter(){
        val myActivity = contexto as MainActivity
    }
}