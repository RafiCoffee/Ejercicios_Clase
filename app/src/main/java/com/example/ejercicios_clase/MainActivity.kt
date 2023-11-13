package com.example.ejercicios_clase

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.*
import android.content.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var pantalla : TextView
    private lateinit var delBt : Button
    private lateinit var acBt : Button
    private lateinit var signoBt : Button
    private lateinit var porcentajeBt : Button
    private lateinit var dividirBt : Button
    private lateinit var sieteBt : Button
    private lateinit var ochoBt : Button
    private lateinit var nueveBt : Button
    private lateinit var multiplicarBt : Button
    private lateinit var cuatroBt : Button
    private lateinit var cincoBt : Button
    private lateinit var seisBt : Button
    private lateinit var restarBt : Button
    private lateinit var unoBt : Button
    private lateinit var dosBt : Button
    private lateinit var tresBt : Button
    private lateinit var sumarBt : Button
    private lateinit var ceroBt : Button
    private lateinit var decimalBt : Button
    private lateinit var igualBt : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        asociarElementos()

        var calculo : Float = 0f
        var numero : String = ""
        var numeros : List<String>

        unoBt.setOnClickListener{
            numero += "1"
            pantalla.text = (numero)
        }

        dosBt.setOnClickListener{
            numero += "2"
            pantalla.text = (numero)
        }

        tresBt.setOnClickListener{
            numero += "3"
            pantalla.text = (numero)
        }

        cuatroBt.setOnClickListener{
            numero += "4"
            pantalla.text = (numero)
        }

        cincoBt.setOnClickListener{
            numero += "5"
            pantalla.text = (numero)
        }

        seisBt.setOnClickListener{
            numero += "6"
            pantalla.text = (numero)
        }

        sieteBt.setOnClickListener{
            numero += "7"
            pantalla.text = (numero)
        }

        ochoBt.setOnClickListener{
            numero += "8"
            pantalla.text = (numero)
        }

        nueveBt.setOnClickListener{
            numero += "9"
            pantalla.text = (numero)
        }
        ceroBt.setOnClickListener{
            numero += "0"
            pantalla.text = (numero)
        }

        decimalBt.setOnClickListener{
            numero += "."
            pantalla.text = (numero)
        }

        delBt.setOnClickListener{
            numero = ""
            pantalla.text = null
        }

        acBt.setOnClickListener{
            numero = ""
            pantalla.text = "0"
        }

        signoBt.setOnClickListener{
            calculo = numero.toFloat()

            if(calculo > 0){
                calculo = calculo - (calculo*2)
            }else{
                calculo = calculo + (-calculo*2)
            }

            numero = calculo.toString()
            if(numero.contains(".")){
                if(numero.endsWith("0")){
                    numero = numero.substringBefore('.')
                }
            }

            pantalla.text = (numero)
        }

        porcentajeBt.setOnClickListener{
            if(!(numero.length - 1).equals(" ")){
                numero += " % "
                pantalla.text = (numero)
            }
        }

        dividirBt.setOnClickListener{
            if(!(numero.length - 1).equals(" ")){
                numero += " / "
                pantalla.text = (numero)
            }
        }

        multiplicarBt.setOnClickListener{
            if(!(numero.length - 1).equals(" ")){
                numero += " x "
                pantalla.text = (numero)
            }
        }

        restarBt.setOnClickListener{
            if(!(numero.length - 1).equals(" ")){
                numero += " - "
                pantalla.text = (numero)
            }
        }

        sumarBt.setOnClickListener{
            if(!(numero.length - 1).equals(" ")){
                numero += " + "
                pantalla.text = (numero)
            }
        }

        igualBt.setOnClickListener{
            numeros = pantalla.text.toString().split(" ")

            when (numeros[1]){
                "/"-> calculo = numeros[0].toFloat() / numeros[2].toFloat()
                "x"-> calculo = numeros[0].toFloat() * numeros[2].toFloat()
                "-"-> calculo = numeros[0].toFloat() - numeros[2].toFloat()
                "+"-> calculo = numeros[0].toFloat() + numeros[2].toFloat()
                "%"-> calculo = (numeros[0].toFloat() / 100) * numeros[2].toFloat()
            }

            numero = calculo.toString()
            if(numero.contains(".")){
                if(numero.endsWith("0")){
                    numero = numero.substringBefore('.')
                }
            }
            pantalla.text = numero
        }
    }
    override fun onStart() {
        super.onStart()
    }
    override fun onResume() {
        super.onResume()
    }
    override fun onPause() {
        super.onPause()
    }
    override fun onRestart() {
        super.onRestart()
    }
    override fun onStop() {
        super.onStop()
    }
    override fun onDestroy() {
        super.onDestroy()
    }

    fun asociarElementos(){
        pantalla = findViewById(R.id.pantallaText)
        delBt = findViewById(R.id.limpiarPantallaBt)
        acBt = findViewById(R.id.borrarPantallaBt)
        signoBt = findViewById(R.id.signoBt)
        porcentajeBt = findViewById(R.id.porcentajeBt)
        dividirBt = findViewById(R.id.dividirBt)
        sieteBt = findViewById(R.id.sieteBt)
        ochoBt = findViewById(R.id.ochoBt)
        nueveBt = findViewById(R.id.nueveBt)
        multiplicarBt = findViewById(R.id.multiplicarBt)
        cuatroBt = findViewById(R.id.cuatroBt)
        cincoBt = findViewById(R.id.cincoBt)
        seisBt = findViewById(R.id.seisBt)
        restarBt = findViewById(R.id.restarBt)
        unoBt = findViewById(R.id.unoBt)
        dosBt = findViewById(R.id.dosBt)
        tresBt = findViewById(R.id.tresBt)
        sumarBt = findViewById(R.id.sumarBt)
        ceroBt = findViewById(R.id.ceroBt)
        decimalBt = findViewById(R.id.decimalesBt)
        igualBt = findViewById(R.id.igualBt)
    }
}