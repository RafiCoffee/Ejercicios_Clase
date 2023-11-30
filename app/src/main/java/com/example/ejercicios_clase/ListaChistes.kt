package com.example.ejercicios_clase

import kotlin.random.Random

object ListaChistes {
    private var chistes: ArrayList<String> = ArrayList(listOf("Soy la Jessy y quiero hacer una domiciliación\n" +
            "\n¿Número de cuenta?\n" +
            "\nAquí tiene\n" +
            "\n¿Y el IBAN?\n" +
            "\nFuera, esperándome en la moto", "¿Sabes qué coche usa Papa Noel? \n" +
            "\nUn Renol", "No sé si conseguiré enamorar a esa chica\n" +
            "\n¿Tú tienes vacas y ovejas?\n" +
            "\nSí\n" +
            "\nPues ya tienes mucho ganado", "Capitán, su mujer está asomada a la ventana del barco\n" +
            "\nEscotilla\n" +
            "\nMe parecía feo decírselo, pero sí, mucho", "Están 1023 terabytes en una fiesta, llega uno más y dice...\n" +
            "\n¿Nos hacemos un peta?", "¿Cuál es el café más peligroso del mundo?\n" +
            "\nEl ex-preso", "¿Qué es una mujer objeto?\n" +
            "\nUna instancia de una mujer con clase", "¿Qué es un terapeuta?\n" +
            "\n1024 Gigapeutas", "¿Que le dice un bit al otro?\n" +
            "\nNos vemos en el bus", "¿Para qué quiere un pastor un compilador?\n" +
            "\nPara tener «OBEJOTAS»", "Sólo hay 10 tipos de personas en este mundo, las que entienden binario y las que no"))

    fun mostrarChiste(): String{
        val chisteIndex: Int = Random.nextInt(0,this.chistes.size)
        val chisteElegido: String = this.chistes.get(chisteIndex)
        return chisteElegido
    }
}