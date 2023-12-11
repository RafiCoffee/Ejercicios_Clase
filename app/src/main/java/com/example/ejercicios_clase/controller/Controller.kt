package com.example.ejercicios_clase.controller

import android.content.Context
import android.widget.Toast
import com.example.ejercicios_clase.MainActivity
import com.example.ejercicios_clase.adapter.AdapterHotel
import com.example.ejercicios_clase.dao.DaoHotels
import com.example.ejercicios_clase.models.Hotel

class Controller(val contexto: Context) {
    private lateinit var listHoteles: MutableList<Hotel>
    private lateinit var adapterHoteles: AdapterHotel

    init {
        initData()
    }

    private fun initData() {
        listHoteles = DaoHotels.mydao.getDataHotels().toMutableList()
    }

    fun loggOut() {
        Toast.makeText(contexto, "Datos mostrados por pantalla", Toast.LENGTH_LONG).show()
        listHoteles.forEach {
            println(it)
        }
    }

    fun setAdapter() {
        val myActivity = contexto as MainActivity
        adapterHoteles = AdapterHotel(
            listHoteles,
            { pos ->
                delCoche(pos)
            },
            { pos ->
                updateCoche(pos)
            }
        )
        myActivity.mainBinding = adapterHoteles
    }

    private fun updateCoche(pos: Int) {

    }

    private fun delCoche(pos: Int) {
        Toast.makeText(contexto, "Borramos el coche con la posicion $pos", Toast.LENGTH_LONG).show()
        listHoteles.removeAt(pos)
        adapterHoteles.notifyItemRemoved(pos)
    }
}