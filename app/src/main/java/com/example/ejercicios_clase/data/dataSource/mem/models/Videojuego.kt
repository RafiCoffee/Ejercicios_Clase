<<<<<<<< HEAD:app/src/main/java/com/example/ejercicios_clase/data/models/Videojuego.kt
package com.example.ejercicios_clase.data.models
========
package com.example.ejercicios_clase.data.dataSource.mem.models
>>>>>>>> origin/Hilt_Y_MvvM:app/src/main/java/com/example/ejercicios_clase/data/dataSource/mem/models/Videojuego.kt

class Videojuego (var titulo: String, var genero: String, var nota: Int, var fechaSalida: String, var image: Int) {
    override fun toString(): String {
        return "Videojuego(titulo='$titulo', genero='$genero', nota='$nota', fechaSalida='$fechaSalida', image='$image')"
    }
}