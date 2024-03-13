package com.example.ejercicios_clase.ui.views

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ejercicios_clase.R
import com.example.ejercicios_clase.data.dataSource.dataBase.dao.UsuarioDao
import com.example.ejercicios_clase.data.dataSource.dataBase.entities.UsuarioEntity
import com.example.ejercicios_clase.data.retrofit.ApiService
import com.example.ejercicios_clase.data.retrofit.RetrofitModule
import com.example.ejercicios_clase.data.retrofit.requests.RequestLoginUsuario
import com.example.ejercicios_clase.data.ui.views.RegistrarUsuarioActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class InicioSesionActivity : AppCompatActivity(){
    private lateinit var emailEdText: EditText
    private lateinit var contrasennaEdText: EditText
    private lateinit var errorText: TextView
    private lateinit var inicioSesionBt: Button
    private lateinit var registrarUsuarioBt: Button

    private lateinit var sPSesion: SharedPreferences

    @Inject
    lateinit var userDao: UsuarioDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        sPSesion = getSharedPreferences("Sesion", MODE_PRIVATE)
        comprobarSesion()

        asociarElementos()
        cargarEventos()

    }

    private fun asociarElementos(){
        emailEdText = findViewById(R.id.emailEditText)
        contrasennaEdText = findViewById(R.id.contrasennaEditText)
        contrasennaEdText.transformationMethod = PasswordTransformationMethod()
        errorText = findViewById(R.id.errorText)
        inicioSesionBt = findViewById(R.id.iniciarSesionBt)
        registrarUsuarioBt = findViewById(R.id.registrarUsuarioBt)
    }

    private fun cargarEventos(){
        inicioSesionBt.setOnClickListener {
            lifecycleScope.launch {
                val idUsuarioLogin = comprobarUsuarios()
                var loguedUsername: String
                if(idUsuarioLogin != -1){
                    withContext(Dispatchers.IO){
                        loguedUsername = userDao.getUserById(idUsuarioLogin).username
                    }
                    abrirApp(loguedUsername)
                }
            }
        }

        registrarUsuarioBt.setOnClickListener {
            val intentRegistrarActivity = Intent(this, RegistrarUsuarioActivity :: class.java)
            try{
                Log.i("PRUEBA", "ENTRA A REGISTRO")
                startActivity(intentRegistrarActivity)
            }catch (e : ActivityNotFoundException){
                Toast.makeText(this, "Error al acceder a la pantalla", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun comprobarUsuarios(): Int {
        val email = emailEdText.text.toString()
        val password = contrasennaEdText.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            errorText.text = getString(R.string.existe_campo_vacio)
            return -1
        }

        val response = withContext(Dispatchers.IO) {
            RetrofitModule.apiService.auth(RequestLoginUsuario(email, password))
        }

        if(response.isSuccessful && response.body()?.result.equals("ok")) {
            withContext(Dispatchers.Main) {
                guardarToken(response.body()!!.token)
            }
            return response.body()!!.idUser
        }else{
            withContext(Dispatchers.Main) {
                Toast.makeText(this@InicioSesionActivity, "" + response.body()?.result, Toast.LENGTH_LONG).show()
                errorText.text = getString(R.string.datos_introducidos_incorrectos)
            }
            return -1
        }
    }

    fun guardarToken(token: String){
        val tokenUsuario: SharedPreferences.Editor = sPSesion.edit()
        tokenUsuario.putString("Token", token)
        tokenUsuario.apply()
    }

    fun comprobarSesion(){
        val sesionIniciada = sPSesion.getBoolean("SesionIniciada", false)
        if (sesionIniciada){
            val ultimoUsernameLogueado = sPSesion.getString("Usuario", "Invitado").toString()
            abrirApp(ultimoUsernameLogueado)
        }
    }

    fun abrirApp(username: String){
        if(!sPSesion.getBoolean("SesionIniciada", false)){
            val usuario : SharedPreferences.Editor = sPSesion.edit()
            usuario.putString("Usuario", username)
            usuario.apply()
        }

        val intentMainActivity = Intent(this, MainActivity :: class.java)

        try{
            startActivity(intentMainActivity)
        }catch (e : ActivityNotFoundException){
            Toast.makeText(this, "Error al acceder a la pantalla", Toast.LENGTH_SHORT).show()
        }
    }
}