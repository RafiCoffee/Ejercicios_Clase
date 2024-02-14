package com.example.ejercicios_clase.ui.views

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicios_clase.ListaUsuarios
import com.example.ejercicios_clase.R

class InicioSesionActivity : AppCompatActivity(){
    private lateinit var usuarioEdText: EditText
    private lateinit var contrasennaEdText: EditText
    private lateinit var errorText: TextView
    private lateinit var inicioSesionBt: Button
    private lateinit var registrarUsuarioBt: Button

    private lateinit var sPSesion: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        sPSesion = getSharedPreferences("Sesion", MODE_PRIVATE)
        comprobarSesion()

        asociarElementos()
        cargarEventos()

    }

    private fun asociarElementos(){
        usuarioEdText = findViewById(R.id.usuarioEditText)
        contrasennaEdText = findViewById(R.id.contrasennaEditText)
        contrasennaEdText.transformationMethod = PasswordTransformationMethod()
        errorText = findViewById(R.id.errorText)
        inicioSesionBt = findViewById(R.id.iniciarSesionBt)
        registrarUsuarioBt = findViewById(R.id.registrarUsuarioBt)
    }

    private fun cargarEventos(){
        inicioSesionBt.setOnClickListener {
            if(comprobarUsuarios()){
                abrirApp()
            }
        }

        registrarUsuarioBt.setOnClickListener {
            val intentRegistrarActivity = Intent(this, RegistrarUsuarioActivity :: class.java)
            try{
                startActivity(intentRegistrarActivity)
            }catch (e : ActivityNotFoundException){
                Toast.makeText(this, "Error al acceder a la pantalla", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun comprobarUsuarios(): Boolean{
        if(usuarioEdText.text.toString().isEmpty() || contrasennaEdText.text.toString().isEmpty()){
            errorText.text = getString(R.string.existe_campo_vacio)
            return false
        }else{
            if(ListaUsuarios.obtenerUsuarios().size != 0){
                for(i in 0 until ListaUsuarios.obtenerUsuarios().size){
                    if (ListaUsuarios.obtenerUsuarios()[i].nombreUsuario == usuarioEdText.text.toString().trim() &&
                        ListaUsuarios.obtenerUsuarios()[i].claveUsuario == contrasennaEdText.text.toString().trim()
                    ){

                        ListaUsuarios.obtenerUsuarios()[i].usuarioIniciado = true
                        return true
                    }
                }
            }
            errorText.text = getString(R.string.datos_introducidos_incorrectos)
            return false
        }
    }

    fun comprobarSesion(){
        val sesionIniciada = sPSesion.getBoolean("SesionIniciada", false)
        if (sesionIniciada){
            abrirApp()
        }
    }

    fun abrirApp(){
        if(!sPSesion.getBoolean("SesionIniciada", false)){
            val nombreUsuario : SharedPreferences.Editor = sPSesion.edit()
            nombreUsuario.putString("Usuario", usuarioEdText.text.toString())
            nombreUsuario.commit()
        }

        val intentMainActivity = Intent(this, MainActivity :: class.java)

        try{
            startActivity(intentMainActivity)
        }catch (e : ActivityNotFoundException){
            Toast.makeText(this, "Error al acceder a la pantalla", Toast.LENGTH_SHORT).show()
        }
    }
}