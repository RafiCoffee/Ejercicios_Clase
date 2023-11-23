package com.example.ejercicios_clase

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewPropertyAnimator
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class DadoActivity : AppCompatActivity() {
    private lateinit var dado: RelativeLayout
    private lateinit var dadoBt: Button
    private lateinit var terminarTurnoBt: Button
    private lateinit var volverBt: ImageButton
    private lateinit var reiniciarBt: ImageButton
    private lateinit var pancartaPlayer1: RelativeLayout
    private lateinit var pancartaPlayer2: RelativeLayout
    private lateinit var puntosPlayer1: TextView
    private lateinit var puntosPlayer2: TextView
    private lateinit var turnoActual: TextView
    private lateinit var victoriaPlayer1: ImageView
    private lateinit var victoriaPlayer2: ImageView
    private lateinit var derrotaPlayer1: ImageView
    private lateinit var derrotaPlayer2: ImageView
    private lateinit var empatePlayer1: ImageView
    private lateinit var empatePlayer2: ImageView
    private var turnoPlayer1: Boolean = true
    private var turnoPlayer2: Boolean = false
    private var finJuego: Boolean = false
    private lateinit var turno: String
    private var numeroAnterior: Int = 1
    private var totalPuntosPlayer1: Int = 0
    private var totalPuntosPlayer2: Int = 0
    private var posicionInicialY: Float = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dado_activity)

        asociarElementos()

        posicionInicialY = dado.translationY
        turno = turnoActual.text.toString()

        reiniciarBt.setOnClickListener {
            reiniciarPantalla()
        }

        if(!finJuego){
            cargarEventos()
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
        dado = findViewById(R.id.LayoutDado)
        dadoBt = findViewById(R.id.dadoBt)
        terminarTurnoBt = findViewById(R.id.pararDadoBt)
        volverBt = findViewById(R.id.volverBt)
        reiniciarBt = findViewById(R.id.reiniciarBt)
        reiniciarBt.isEnabled = false
        pancartaPlayer1 = findViewById(R.id.pancartaDadosPlayer1)
        pancartaPlayer2 = findViewById(R.id.pancartaDadosPlayer2)
        puntosPlayer1 = findViewById(R.id.puntosPlayer1)
        puntosPlayer2 = findViewById(R.id.puntosPlayer2)
        turnoActual = findViewById(R.id.turnoActual)
        victoriaPlayer1 = findViewById(R.id.victoriaPlayer1)
        victoriaPlayer2 = findViewById(R.id.victoriaPlayer2)
        derrotaPlayer1 = findViewById(R.id.derrotaPlayer1)
        derrotaPlayer2 = findViewById(R.id.derrotaPlayer2)
        empatePlayer1 = findViewById(R.id.empatePlayer1)
        empatePlayer2 = findViewById(R.id.empatePlayer2)
    }

    fun cargarEventos(){
        dadoBt.setOnClickListener {
            dadoBt.isEnabled = false
            dado.rotation = 0f
            dado.translationY = posicionInicialY

            var numeroActual: Int = 0
            val animacionDado1: ViewPropertyAnimator = dado.animate().rotation(3600000000000f).translationY(-275f).setDuration(500)

            animacionDado1.withEndAction {
                val animacionDado2: ViewPropertyAnimator = dado.animate().rotation(0f).translationY(posicionInicialY).setDuration(275)

                val numeroGenerado = generarNumeroAleatorio()

                if(turnoPlayer1){
                    numeroActual = cambiarDado(dado, numeroGenerado)

                    if(numeroActual == 1){
                        totalPuntosPlayer1 /= numeroAnterior
                        terminarTurno(totalPuntosPlayer1)
                    }else{
                        totalPuntosPlayer1 += numeroActual
                        numeroAnterior = numeroActual
                    }

                    turnoActual.text = turno + "\n" + totalPuntosPlayer1

                }else if(turnoPlayer2){
                    numeroActual = cambiarDado(dado, numeroGenerado)

                    if(numeroActual == 1){
                        totalPuntosPlayer2 /= numeroAnterior
                        numeroAnterior = numeroActual
                        terminarTurno(totalPuntosPlayer2)

                    }else{
                        totalPuntosPlayer2 += numeroActual
                        numeroAnterior = numeroActual
                    }

                    turnoActual.text = turno + "\n" + totalPuntosPlayer2

                }

                animacionDado2.start()
            }
            animacionDado1.start()
            dadoBt.isEnabled = true
        }

        terminarTurnoBt.setOnClickListener {
            if(turnoPlayer1){
                terminarTurno(totalPuntosPlayer1)
            }else if(turnoPlayer2){
                terminarTurno(totalPuntosPlayer2)
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

    fun reiniciarPantalla(){
        val intentDadoActivity = Intent(this, DadoActivity :: class.java)

        try{
            startActivity(intentDadoActivity)
        }catch (e : ActivityNotFoundException){
            Toast.makeText(this, "Error reiniciar el juego", Toast.LENGTH_SHORT).show()
        }
    }

    fun generarNumeroAleatorio() : Int{
        val numeroDado : Int = Random.nextInt(1,7)
        return numeroDado
    }
    
    fun cambiarDado(dadoActual: RelativeLayout, numeroGenerado: Int): Int{
        try {
            val parametros = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
            parametros.addRule(RelativeLayout.CENTER_IN_PARENT)
            when (numeroGenerado) {
                1 -> {
                    val nuevoDado = layoutInflater.inflate(R.layout.dado1, null)
                    nuevoDado.layoutParams = parametros
                    dadoActual.removeView(dadoActual.getChildAt(dadoActual.childCount - 1))
                    dadoActual.addView(nuevoDado)
                }

                2 -> {
                    val nuevoDado = layoutInflater.inflate(R.layout.dado2, null)
                    nuevoDado.layoutParams = parametros
                    dadoActual.removeView(dadoActual.getChildAt(dadoActual.childCount - 1))
                    dadoActual.addView(nuevoDado)
                }

                3 -> {
                    val nuevoDado = layoutInflater.inflate(R.layout.dado3, null)
                    nuevoDado.layoutParams = parametros
                    dadoActual.removeView(dadoActual.getChildAt(dadoActual.childCount - 1))
                    dadoActual.addView(nuevoDado)
                }

                4 -> {
                    val nuevoDado = layoutInflater.inflate(R.layout.dado4, null)
                    nuevoDado.layoutParams = parametros
                    dadoActual.removeView(dadoActual.getChildAt(dadoActual.childCount - 1))
                    dadoActual.addView(nuevoDado)
                }

                5 -> {
                    val nuevoDado = layoutInflater.inflate(R.layout.dado5, null)
                    nuevoDado.layoutParams = parametros
                    dadoActual.removeView(dadoActual.getChildAt(dadoActual.childCount - 1))
                    dadoActual.addView(nuevoDado)
                }

                6 -> {
                    val nuevoDado = layoutInflater.inflate(R.layout.dado6, null)
                    nuevoDado.layoutParams = parametros
                    dadoActual.removeView(dadoActual.getChildAt(dadoActual.childCount - 1))
                    dadoActual.addView(nuevoDado)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return numeroGenerado
    }

    fun terminarTurno(totalPuntos: Int){

        Thread.sleep(300)

        if(turnoPlayer1){
            turnoPlayer1 = false
            turnoPlayer2 = true

            turno = getString(R.string.player_2)
            turnoActual.text = getString(R.string.player_2)

            puntosPlayer1.text = totalPuntos.toString()

            val animacionPancartaP1_1: ViewPropertyAnimator = pancartaPlayer1.animate().translationX(-30f).setDuration(150)

            animacionPancartaP1_1.withEndAction {
                val animacionPancartaP1_2: ViewPropertyAnimator = pancartaPlayer1.animate().translationX(-80f).setDuration(250)
                animacionPancartaP1_2.start()
            }
            animacionPancartaP1_1.start()
        }else if(turnoPlayer2){
            turnoPlayer2 = false

            puntosPlayer2.text = totalPuntos.toString()

            val animacionPancartaP2_1: ViewPropertyAnimator = pancartaPlayer2.animate().translationX(30f).setDuration(150)

            animacionPancartaP2_1.withEndAction {
                val animacionPancartaP2_2: ViewPropertyAnimator = pancartaPlayer2.animate().translationX(80f).setDuration(250)

                animacionPancartaP2_2.withEndAction {
                    terminarJuego()
                }
                animacionPancartaP2_2.start()
            }
            animacionPancartaP2_1.start()
        }
    }
    fun terminarJuego(){
        finJuego = true

        compararResultados()

    }

    fun compararResultados(){
        val puntosPlayer1 = puntosPlayer1.text.toString()
        val puntosPlayer2 = puntosPlayer2.text.toString()

        val animacionEsconderBt: ViewPropertyAnimator = terminarTurnoBt.animate().scaleY(0f).setDuration(400)
        val animacionAparecerBt: ViewPropertyAnimator = reiniciarBt.animate().scaleY(1f).setDuration(400)

        animacionEsconderBt.withEndAction { terminarTurnoBt.isEnabled = false }
        animacionEsconderBt.start()
        animacionAparecerBt.withEndAction { reiniciarBt.isEnabled = true }
        animacionAparecerBt.start()

        if(puntosPlayer1.toInt() < puntosPlayer2.toInt()){
            val animacionVictoria: ViewPropertyAnimator = victoriaPlayer2.animate().scaleX(1f).setDuration(300)
            val animacionDerrota: ViewPropertyAnimator = derrotaPlayer1.animate().scaleX(1f).setDuration(300)
            animacionVictoria.start()
            animacionDerrota.start()
            turnoActual.text = "El jugador 2 ha ganado la partida"
        }else if(puntosPlayer1.toInt() > puntosPlayer2.toInt()){
            val animacionVictoria: ViewPropertyAnimator = victoriaPlayer1.animate().scaleX(1f).setDuration(300)
            val animacionDerrota: ViewPropertyAnimator = derrotaPlayer2.animate().scaleX(1f).setDuration(300)
            animacionVictoria.start()
            animacionDerrota.start()
            turnoActual.text = "El jugador 1 ha ganado la partida"
        }else{
            val animacionEmpate1: ViewPropertyAnimator = empatePlayer1.animate().scaleX(1f).setDuration(300)
            val animacionEmpate2: ViewPropertyAnimator = empatePlayer2.animate().scaleX(1f).setDuration(300)
            animacionEmpate1.start()
            animacionEmpate2.start()
            turnoActual.text = "Los jugadores han quedado en empate"
        }
    }
}