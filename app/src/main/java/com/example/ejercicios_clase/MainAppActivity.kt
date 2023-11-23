package com.example.ejercicios_clase

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.Settings
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import java.util.Calendar

class MainAppActivity : AppCompatActivity(){
    private lateinit var llamadaBt : ImageButton
    private lateinit var internetBt : ImageButton
    private lateinit var dadoBt : ImageButton
    private lateinit var alarmaBt : ImageButton
    private lateinit var ajustesBt : ImageButton
    private lateinit var chistesBt : ImageButton
    private lateinit var cerrarSesionBt: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejer_app)

        asociarElementos()
        cargarEventos()
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

    private fun asociarElementos(){
        llamadaBt = findViewById(R.id.LlamadaBt)
        internetBt = findViewById(R.id.InternetBt)
        dadoBt = findViewById(R.id.DadoBt)
        alarmaBt = findViewById(R.id.AlarmaBt)
        ajustesBt = findViewById(R.id.AjustesBt)
        chistesBt = findViewById(R.id.ChistesBt)
        cerrarSesionBt = findViewById(R.id.cerrarSesionBt)
    }

    private fun cargarEventos(){

        llamadaBt.setOnClickListener{
            val intentLlamada = Intent(this, LlamadaActivity :: class.java)

            try {
                startActivity(intentLlamada)
            }catch (e : ActivityNotFoundException){
                Toast.makeText(this, "Error al acceder a la pantalla", Toast.LENGTH_SHORT).show()
            }
        }

        internetBt.setOnClickListener{
            val url : String = "https://danielmm7.itch.io/blossom-by-gears"

            val intentInternet = Intent(Intent.ACTION_VIEW, Uri.parse(url))

            try {
                startActivity(intentInternet)
            }catch (e : ActivityNotFoundException){
                Toast.makeText(this, "Error al acceder a la página", Toast.LENGTH_SHORT).show()
            }
        }

        dadoBt.setOnClickListener {
            val intentDados = Intent(this, DadoActivity :: class.java)

            try{
                startActivity(intentDados)
            }catch (e : ActivityNotFoundException){
                Toast.makeText(this, "Error al acceder a la pantalla", Toast.LENGTH_SHORT).show()
            }
        }

        alarmaBt.setOnClickListener{
            val horaActual : Calendar = Calendar.getInstance()
            horaActual.add(Calendar.MINUTE, 2)

            val intentAlarma = Intent(AlarmClock.ACTION_SET_ALARM)
            intentAlarma.putExtra(AlarmClock.EXTRA_MESSAGE, "Alarma para 2 minutos")
            intentAlarma.putExtra(AlarmClock.EXTRA_HOUR, horaActual.get(Calendar.HOUR_OF_DAY))
            intentAlarma.putExtra(AlarmClock.EXTRA_MINUTES, horaActual.get(Calendar.MINUTE))

            try {
                startActivity(intentAlarma)
            }catch (e : ActivityNotFoundException){
                Toast.makeText(this, "Error al crear la alarma", Toast.LENGTH_SHORT).show()
            }
        }

        ajustesBt.setOnClickListener{
            val intentAjustes = Intent(Settings.ACTION_SETTINGS)

            try {
                startActivity(intentAjustes)
            }catch (e : ActivityNotFoundException){
                Toast.makeText(this, "Error al acceder a los ajustes", Toast.LENGTH_SHORT).show()
            }
        }

        cerrarSesionBt.setOnClickListener {
            val intentCerrarSesion = Intent(this, InicioSesionActivity :: class.java)

            try {
                startActivity(intentCerrarSesion)
            }catch (e : ActivityNotFoundException){
                Toast.makeText(this, "Error al acceder a la pantalla", Toast.LENGTH_SHORT).show()
            }
        }

    }

}