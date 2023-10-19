package com.example.ejercicios_clase

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private var x: Int = 1
private var y: Int = 2
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "X: '$x' Y: '$y'" )
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Se llama después de onCreate. Santi, muestro la pantalla. El usuario aún no puede interaccionar")
        x++; y++
        Log.d(TAG, "X: '$x' Y: '$y'" )
        val a = A("Ruben", "Rafi", 20, true)
        Toast.makeText(this, a.toString(), Toast.LENGTH_LONG).show()
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "El usuario ya puede interaccionar con la pantalla")
        x++; y--
        Log.d(TAG, "X: '$x' Y: '$y'" )
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Pierdo el foco de la pantalla. Boton home")
        x--; y--
        Log.d(TAG, "X: '$x' Y: '$y'" )
    }
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "Vuelvo a estar visible para santi.")
        x--; y++
        Log.d(TAG, "X: '$x' Y: '$y'" )
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Dejo de estar visible para el usuario. Otra App, S.O.")
        x--; y++
        Log.d(TAG, "X: '$x' Y: '$y'" )
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "La actividad muere.")
        x++; y++
        Log.d(TAG, "X: '$x' Y: '$y'" )
    }
}