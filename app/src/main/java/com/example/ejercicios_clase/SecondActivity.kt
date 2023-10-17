package com.example.ejercicios_clase

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private lateinit var boton : Button
private lateinit var texto : TextView
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        boton = findViewById(R.id.PruebaSaludo)
        texto = findViewById(R.id.Prueba)
    }

    private fun initEvent(){
        boton.setOnClickListener{
            Toast.makeText(this, "Hola, funcioné", Toast.LENGTH_LONG).show()
            texto.text = "Hola, funcioné"
        }
    }
}