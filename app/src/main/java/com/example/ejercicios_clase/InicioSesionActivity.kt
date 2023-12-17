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

class InicioSesionActivity : AppCompatActivity(){
    private lateinit var usuarioEdText: EditText
    private lateinit var contrasennaEdText: EditText
    private lateinit var errorText: TextView
    private lateinit var inicioSesionBt: Button
    private lateinit var registrarUsuarioBt: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

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
        errorText = findViewById(R.id.errorText)
        inicioSesionBt = findViewById(R.id.iniciarSesionBt)
        registrarUsuarioBt = findViewById(R.id.registrarUsuarioBt)
    }

    fun cargarEventos(){
        inicioSesionBt.setOnClickListener {
            if(comprobarUsuarios()){
                val intentMainActivity = Intent(this, MainActivity :: class.java)

                try{
                    startActivity(intentMainActivity)
                }catch (e : ActivityNotFoundException){
                    Toast.makeText(this, "Error al acceder a la pantalla", Toast.LENGTH_SHORT).show()
                }
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

    fun comprobarUsuarios(): Boolean{
        if(usuarioEdText.text.toString().isEmpty() || contrasennaEdText.text.toString().isEmpty()){
            errorText.text = "Existe algún campo vacío"
            return false
        }else{
            if(!ListaUsuarios.obtenerUsuarios().size.equals(0)){
                for(i in 0 until ListaUsuarios.obtenerUsuarios().size){
                    if (ListaUsuarios.obtenerUsuarios().get(i).nombreUsuario.equals(usuarioEdText.text.toString()) &&
                        ListaUsuarios.obtenerUsuarios().get(i).claveUsuario.equals(contrasennaEdText.text.toString())){

                        ListaUsuarios.obtenerUsuarios().get(i).usuarioIniciado = true
                        return true
                    }
                }
            }
            errorText.text = "Usuario o contraseña introducidos incorrectos"
            return false;
        }
    }
}