package com.example.ejercicios_clase.data.dataSource.mem.models

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.ejercicios_clase.data.retrofit.RetrofitModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repositorio {
    companion object{
        var videojuegos: List<Videojuego> = emptyList()

        suspend fun cargarVideojuegos(apiKey: String){
            val response = withContext(Dispatchers.IO){
                RetrofitModule.apiService.getVideojuegos(apiKey)
            }

            if(response.isSuccessful && response.body()?.result.equals("ok")){
                withContext(Dispatchers.Main){
                    Log.i("TAG", apiKey + " --- " + response.body())
                    videojuegos = response.body()!!.videojuegos
                }
            }
        }
    }

}