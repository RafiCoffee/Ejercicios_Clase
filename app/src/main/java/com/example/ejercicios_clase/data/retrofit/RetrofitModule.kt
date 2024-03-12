package com.example.ejercicios_clase.data.retrofit

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val URL_RETROFIT = "http://10.0.2.2/api-videojuegos/"

    val apiService: ApiService by lazy {
        Retrofit.Builder().baseUrl(URL_RETROFIT)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }
}