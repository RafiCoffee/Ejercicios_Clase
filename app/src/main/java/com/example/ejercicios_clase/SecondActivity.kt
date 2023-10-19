package com.example.ejercicios_clase

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private lateinit var saludoBt: Button
private lateinit var noSaludoBt: Button
private lateinit var saludoClaseBt: Button
class SecondActivity : AppCompatActivity()/*, View.OnClickListener*/ {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saludoBt = findViewById(R.id.Saludo)
        noSaludoBt = findViewById(R.id.NoSaludo)
        saludoClaseBt = findViewById(R.id.SaludoClase)

        saludoBt.setOnClickListener{
            Toast.makeText(this, "Hola usuario", Toast.LENGTH_LONG).show()
        }

        noSaludoBt.setOnClickListener{
            Toast.makeText(this, "No te saludo", Toast.LENGTH_LONG).show()
        }

        saludoClaseBt.setOnClickListener{
            Toast.makeText(this, "Hola clase", Toast.LENGTH_LONG).show()
        }

        /*saludoBt.setOnClickListener(this)
        noSaludoBt.setOnClickListener(this)
        saludoClaseBt.setOnClickListener(this)*/
    }

    /*override fun onClick(botonPulsado: View){
        when(botonPulsado.id){
            saludoBt.id -> Toast.makeText(this, "Hola usuario", Toast.LENGTH_LONG).show()

            noSaludoBt.id -> Toast.makeText(this, "No te saludo", Toast.LENGTH_LONG).show()

            saludoClaseBt.id -> Toast.makeText(this, "Hola clase", Toast.LENGTH_LONG).show()
        }
    }

    fun capturarEvento(botonPulsado: View){
        when(botonPulsado.id){
            saludoBt.id -> Toast.makeText(this, "Hola usuario", Toast.LENGTH_LONG).show()

            noSaludoBt.id -> Toast.makeText(this, "No te saludo", Toast.LENGTH_LONG).show()

            saludoClaseBt.id -> Toast.makeText(this, "Hola clase", Toast.LENGTH_LONG).show()
        }
    }*/



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

}