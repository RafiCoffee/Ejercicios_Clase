package com.example.ejercicios_clase

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import java.util.Locale

class ChistesActivity: AppCompatActivity(), TextToSpeech.OnInitListener {
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
        super.onPause()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onStop() {
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
        chisteText = findViewById(R.id.chisteText)
        chisteBt = findViewById(R.id.chisteBt)
        cargaChiste = findViewById(R.id.cargadorChistes)

        vozChistes = TextToSpeech(this, this)
    }

    fun cargarEventos(){
        chisteBt.setOnClickListener {
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