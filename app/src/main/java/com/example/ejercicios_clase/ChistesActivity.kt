package com.example.ejercicios_clase

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import java.util.Locale

class ChistesActivity: AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var volverBt: ImageButton
    private lateinit var chisteText: TextView
    private lateinit var chisteBt: Button
    private lateinit var cargaChiste: ProgressBar
    private lateinit var vozChistes: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chistes)

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
        if (vozChistes.isSpeaking) {
            vozChistes.stop()
        }

        super.onPause()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onStop() {
        if (vozChistes.isSpeaking) {
            vozChistes.stop()
        }

        super.onStop()
    }

    override fun onDestroy() {
        if (vozChistes.isSpeaking) {
            vozChistes.stop()
        }
        vozChistes.shutdown()

        super.onDestroy()
    }
    fun asociarElementos(){
        volverBt = findViewById(R.id.volverBt)
        chisteText = findViewById(R.id.chisteText)
        chisteBt = findViewById(R.id.chisteBt)
        cargaChiste = findViewById(R.id.cargadorChistes)

        vozChistes = TextToSpeech(this, this)
    }

    fun cargarEventos(){
        chisteBt.setOnClickListener {
            if(!vozChistes.isSpeaking){
                cargaChiste.visibility = View.VISIBLE
                chisteText.visibility = View.INVISIBLE
                chisteText.typeface = ResourcesCompat.getFont(this, R.font.lost_emerald_font)
                val chiste = ListaChistes.mostrarChiste()

                chisteBt.postDelayed({
                    cargaChiste.visibility = View.INVISIBLE
                    chisteText.visibility = View.VISIBLE

                    chisteText.text = chiste
                    vozChistes.speak(chiste, TextToSpeech.QUEUE_FLUSH, null, null)
                }, 4000)
            }else{
                Toast.makeText(this, "Aún no se ha terminado de leer el chiste o no se ha inicializado por completo", Toast.LENGTH_SHORT).show()
            }
        }

        volverBt.setOnClickListener {
            val intentMainActivity = Intent(this, MainAppActivity :: class.java)

            try{
                startActivity(intentMainActivity)
            }catch (e : ActivityNotFoundException){
                Toast.makeText(this, "Error al acceder a la pantalla", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {

            val result = vozChistes.setLanguage(Locale("es", "ES"))

            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                Toast.makeText(this, "Error al inicializar el lector de chistes", Toast.LENGTH_SHORT).show()
            }
        }
    }


}