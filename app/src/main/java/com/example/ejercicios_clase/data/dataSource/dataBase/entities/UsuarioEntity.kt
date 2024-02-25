package com.example.ejercicios_clase.data.dataSource.dataBase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UsuarioEntity (
    @PrimaryKey(autoGenerate = true) var id: Int,
    val username: String,
    val claveUsuario: String,
    val sexo: Boolean,
    val tipoUsuario: Int
)