package com.example.ejercicios_clase

object ListaUsuarios {
    private var usuarios: ArrayList<Usuario> = ArrayList(listOf(Usuario("admin", "admin", false)))
    fun annadirUsuario(nuevoUsuario: Usuario){ this.usuarios.add(nuevoUsuario) }

    fun obtenerUsuarios(): ArrayList<Usuario> { return this.usuarios }
}