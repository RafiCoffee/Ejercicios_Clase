package com.example.ejercicios_clase.data.dataSource.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ejercicios_clase.data.dataSource.dataBase.dao.UsuarioDao
import com.example.ejercicios_clase.data.dataSource.dataBase.entities.UsuarioEntity

@Database(entities = [UsuarioEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun usersDao() : UsuarioDao
}