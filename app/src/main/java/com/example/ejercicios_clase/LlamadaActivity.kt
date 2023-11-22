package com.example.ejercicios_clase

import android.Manifest.permission.CALL_PHONE
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class LlamadaActivity : AppCompatActivity() {

    private lateinit var llamadaBt : ImageButton
    private lateinit var cuadroTexto : EditText
    private var REQUEST_CODE_LLAMADA : Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llamada_app)

        llamadaBt = findViewById(R.id.LlamadaDirectaBt)
        cuadroTexto = findViewById(R.id.CuadroTelefono)

        llamadaBt.setOnClickListener{
            if(cuadroTexto.text == null){
                AppLlamada()
            }else{
                try {
                    var comprobarTelefonoInt : Int = cuadroTexto.text.toString().toInt()

                    Toast.makeText(this, "Llamando al número introducido", Toast.LENGTH_SHORT).show()
                    MarcarLlamada(cuadroTexto.text.toString())
                }catch (e : NumberFormatException){
                    Toast.makeText(this, "El numero introducido no es válido, redirigiendo a la app de llamadas", Toast.LENGTH_SHORT).show()
                    AppLlamada()
                }
            }
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

    private fun AppLlamada(){
        val intentLlamada = Intent(Intent.ACTION_DIAL)

        try {
            startActivity(intentLlamada)
        }catch (e : ActivityNotFoundException){
            Toast.makeText(this, "Error al acceder a la aplicación", Toast.LENGTH_SHORT).show()
        }
    }

    private fun MarcarLlamada(telefono : String){
        val intentLlamada = Intent(Intent.ACTION_CALL)

        if(ContextCompat.checkSelfPermission(this, CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            val uri = Uri.parse("tel:$telefono")
            intentLlamada.data = uri
            try {
                startActivity(intentLlamada)
            }catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Error no se ha podido llamar al número introducido", Toast.LENGTH_SHORT).show()
            }
        }else{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(arrayOf<String>(CALL_PHONE), REQUEST_CODE_LLAMADA)
            }
        }
    }
}