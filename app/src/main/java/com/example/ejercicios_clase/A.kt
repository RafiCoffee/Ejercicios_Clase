package com.example.ejercicios_clase

class A constructor(nombre: String, apodo: String, edad: Int, existe: Boolean){
    var nombre: String
    var apodo: String
    var edad: Int
    var existe: Boolean

    init {
        this.nombre = nombre
        this.apodo = apodo
        this.edad = edad
        this.existe = existe
    }

    override fun toString(): String {
        return "A(nombre='$nombre', apodo='$apodo', edad=$edad, existe=$existe)"
    }


}