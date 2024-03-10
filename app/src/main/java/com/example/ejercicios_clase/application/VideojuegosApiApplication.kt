package com.example.ejercicios_clase.application

import android.app.Application
import com.example.ejercicios_clase.data.dataSource.dataBase.AppDataBase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class VideojuegosApiApplication : Application() {}