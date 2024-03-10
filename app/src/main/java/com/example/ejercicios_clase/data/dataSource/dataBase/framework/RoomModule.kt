package com.example.ejercicios_clase.data.dataSource.dataBase.framework

import android.app.Application
import androidx.room.Room
import com.example.ejercicios_clase.data.dataSource.dataBase.AppDataBase
import com.example.ejercicios_clase.data.dataSource.dataBase.dao.UsuarioDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    fun provideDataBase(app: Application): AppDataBase{
        return Room.databaseBuilder(
            app, AppDataBase::class.java,
            "Videojuegos"
        ).build()
    }

    @Provides
    fun provideUsuarioDao(dataBase: AppDataBase): UsuarioDao{
        return dataBase.usersDao()
    }
}