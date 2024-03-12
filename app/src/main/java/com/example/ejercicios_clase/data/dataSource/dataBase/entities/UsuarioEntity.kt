package com.example.ejercicios_clase.data.dataSource.dataBase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UsuarioEntity (
    @PrimaryKey(autoGenerate = true) var id: Int,
    val email: String,
    val password: String,
    val username: String,
    val disponible: Int
)