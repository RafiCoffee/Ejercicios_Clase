package com.example.ejercicios_clase

object ListaUsuarios {
    private var usuarios: ArrayList<Usuario> = ArrayList()
    fun annadirUsuario(nuevoUsuario: Usuario){ this.usuarios.add(nuevoUsuario) }

    fun obtenerUsuarios(): ArrayList<Usuario> { return this.usuarios }
}