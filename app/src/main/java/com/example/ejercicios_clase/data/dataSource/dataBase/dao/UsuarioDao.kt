package com.example.ejercicios_clase.data.dataSource.dataBase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ejercicios_clase.data.dataSource.dataBase.entities.UsuarioEntity

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): List<UsuarioEntity>
    @Query("DELETE FROM users")
    fun eliminarTodosLosUsuarios()
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUser(vararg user: UsuarioEntity)
    @Update
    fun updateUser(user: UsuarioEntity)
    @Delete
    fun deleteUser(user: UsuarioEntity)
}