package com.example.ejercicios_clase

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.*
import android.content.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var imagen1 : ImageView
    private lateinit var imagen2 : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imagen1 = findViewById(R.id.Imagen1)
        imagen2 = findViewById(R.id.Imagen2)

        imagen1.setOnClickListener(){
            val intent = Intent(this, Activity_Secundaria :: class.java)
            startActivity(intent)
        }

        imagen2.setOnClickListener(){
            val intent = Intent(this, Activity_Secundaria2 :: class.java)
            startActivity(intent)
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
}