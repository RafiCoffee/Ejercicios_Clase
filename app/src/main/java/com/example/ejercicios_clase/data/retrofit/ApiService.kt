package com.example.ejercicios_clase.data.retrofit

import com.example.ejercicios_clase.data.dataSource.mem.models.Videojuego
import com.example.ejercicios_clase.data.retrofit.requests.RequestAddVideojuego
import com.example.ejercicios_clase.data.retrofit.requests.RequestLoginUsuario
import com.example.ejercicios_clase.data.retrofit.requests.RequestRegisterUsuario
import com.example.ejercicios_clase.data.retrofit.requests.RequestUpdateVideojuego
import com.example.ejercicios_clase.data.retrofit.responses.ResponseDeleteVideojuego
import com.example.ejercicios_clase.data.retrofit.responses.ResponseLoginUsuario
import com.example.ejercicios_clase.data.retrofit.responses.ResponseRegisterUsuario
import com.example.ejercicios_clase.data.retrofit.responses.ResponseUpdateVideojuego
import com.example.ejercicios_clase.data.retrofit.responses.ResponseVideojuegos
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query
import javax.inject.Inject

interface ApiService {
    @POST("endp/auth")
    suspend fun auth(@Body loginUsuario: RequestLoginUsuario): Response<ResponseLoginUsuario>

    @POST("endp/registro")
    suspend fun registro(@Body registerUsuario: RequestRegisterUsuario): Response<ResponseRegisterUsuario>

    @GET("endp/videojuego")
    suspend fun getVideojuegos(@Header("api-key") apikey: String): Response<ResponseVideojuegos>

    @POST("endp/videojuego")
    suspend fun addVideojuego(@Header("api-key") apikey: String, @Query("id") videojuegoId: String): Response<ResponseDeleteVideojuego>

    @DELETE("endp/videojuego")
    suspend fun borrarVideojuego(@Header("api-key") apikey: String, @Body videojuegoData: RequestAddVideojuego): Response<ResponseUpdateVideojuego>

    @PUT("endp/videojuego")
    suspend fun editarVideojuego(@Header("api-key") apikey: String, @Query("id") videojuegoId: String, @Body videojuegoData: RequestUpdateVideojuego): Response<ResponseUpdateVideojuego>
}