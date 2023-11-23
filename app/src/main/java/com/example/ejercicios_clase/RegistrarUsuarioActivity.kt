package com.example.ejercicios_clase

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrarUsuarioActivity: AppCompatActivity() {
    private lateinit var usuarioEdText: EditText
    private lateinit var contrasennaEdText: EditText
    private lateinit var confirmarContrasennaEdText: EditText
    private lateinit var errorText: TextView
    private lateinit var registrarUsuarioBt: Button

    private val listaUsuarios = ListaUsuarios
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)

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

    fun asociarElementos(){
        usuarioEdText = findViewById(R.id.usuarioEditText)
        contrasennaEdText = findViewById(R.id.contrasennaEditText)
        contrasennaEdText.transformationMethod = PasswordTransformationMethod()
        confirmarContrasennaEdText = findViewById(R.id.confirmarContrasennaEditText)
        confirmarContrasennaEdText.transformationMethod = PasswordTransformationMethod()
        errorText = findViewById(R.id.errorText)
        registrarUsuarioBt = findViewById(R.id.registrarUsuarioBt)
    }

    fun cargarEventos(){
        registrarUsuarioBt.setOnClickListener {
            if(comprobarRegistro()){
                val intentIniciarSesionActivity = Intent(this, InicioSesionActivity :: class.java)

                try{
                    startActivity(intentIniciarSesionActivity)
                }catch (e : ActivityNotFoundException){
                    Toast.makeText(this, "Error al acceder a la pantalla", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun comprobarRegistro(): Boolean{
        if(usuarioEdText.text.toString().isEmpty() || contrasennaEdText.text.toString().isEmpty() || confirmarContrasennaEdText.text.toString().isEmpty()){
            errorText.text = "Existe algún campo vacío"
            return false
        }else if(!contrasennaEdText.text.toString().equals(confirmarContrasennaEdText.text.toString())){
            errorText.text = "Las contraseñas no coinciden"
            return false
        }else{
            listaUsuarios.annadirUsuario(Usuario(usuarioEdText.text.toString(), contrasennaEdText.text.toString()))
            return true
        }
    }
}