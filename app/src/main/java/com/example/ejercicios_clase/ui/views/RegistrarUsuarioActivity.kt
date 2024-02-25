package com.example.ejercicios_clase.ui.views

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ejercicios_clase.ListaUsuarios
import com.example.ejercicios_clase.R
import com.example.ejercicios_clase.data.dataSource.dataBase.AppDataBase
import com.example.ejercicios_clase.data.dataSource.dataBase.dao.UsuarioDao
import com.example.ejercicios_clase.data.dataSource.dataBase.entities.UsuarioEntity
import com.example.ejercicios_clase.models.Usuario
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class RegistrarUsuarioActivity: AppCompatActivity() {
    private lateinit var volverBt: ImageButton
    private lateinit var imagenUsuario: ImageView
    private lateinit var usuarioEdText: EditText
    private lateinit var contrasennaEdText: EditText
    private lateinit var confirmarContrasennaEdText: EditText
    private lateinit var seleccionSexo: RadioGroup
    private lateinit var tipoUsuario: Spinner
    private lateinit var condiciones: CheckBox
    private lateinit var errorText: TextView
    private lateinit var registrarUsuarioBt: Button

    private var esHombre: Boolean = true
    private var rolUsuario: Int = 0

    @Inject
    lateinit var userDao: UsuarioDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)

        asociarElementos()
        cargarEventos()

    }

    private fun asociarElementos(){
        volverBt = findViewById(R.id.volverBt)
        imagenUsuario = findViewById(R.id.imagenUsuario)
        usuarioEdText = findViewById(R.id.usuarioEditText)
        contrasennaEdText = findViewById(R.id.contrasennaEditText)
        contrasennaEdText.transformationMethod = PasswordTransformationMethod()
        confirmarContrasennaEdText = findViewById(R.id.confirmarContrasennaEditText)
        confirmarContrasennaEdText.transformationMethod = PasswordTransformationMethod()
        seleccionSexo = findViewById(R.id.seleccionSexo)
        tipoUsuario = findViewById(R.id.tipoUsuario)
        condiciones = findViewById(R.id.aceptarCondiciones)
        errorText = findViewById(R.id.errorText)
        registrarUsuarioBt = findViewById(R.id.registrarUsuarioBt)
    }

    private fun cargarEventos(){
        val intentIniciarSesionActivity = Intent(this, InicioSesionActivity :: class.java)

        registrarUsuarioBt.setOnClickListener {
            if(comprobarRegistro()){
                try{
                    startActivity(intentIniciarSesionActivity)
                }catch (e : ActivityNotFoundException){
                    Toast.makeText(this, "Error al acceder a la pantalla", Toast.LENGTH_SHORT).show()
                }
            }
        }

        seleccionSexo.setOnCheckedChangeListener { _, checkedId ->
            val sexo = findViewById<RadioButton>(checkedId)
            esHombre = sexo.text.equals("Hombre")

            if(esHombre == true){
                imagenUsuario.setImageResource(R.drawable.usuario_hombre_logo)
            }else if (esHombre == false){
                imagenUsuario.setImageResource(R.drawable.usuario_mujer_logo)
            }
        }

        tipoUsuario.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                // 'position' contiene el índice de la opción seleccionada
                // Puedes usar 'position' según tus necesidades
                rolUsuario = position
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Acción a realizar cuando no hay nada seleccionado (opcional)
            }
        }

        volverBt.setOnClickListener {
            try{
                startActivity(intentIniciarSesionActivity)
            }catch (e : ActivityNotFoundException){
                Toast.makeText(this, "Error al acceder a la pantalla", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun comprobarRegistro(): Boolean{
        if(usuarioEdText.text.toString().isEmpty() || contrasennaEdText.text.toString().isEmpty() || confirmarContrasennaEdText.text.toString().isEmpty() || esHombre == null){
            errorText.text = getString(R.string.existe_campo_vacio)
            return false
        }else if(contrasennaEdText.text.toString().trim() != confirmarContrasennaEdText.text.toString().trim()){
            errorText.text = getString(R.string.claves_no_coinciden)
            return false
        }else{
            if(!condiciones.isChecked){
                errorText.text = getString(R.string.debes_aceptar_condiciones)
                return false
            }else{
                return if(rolUsuario == 0 && !contrasennaEdText.text.toString().equals("admin")){
                    Toast.makeText(this, contrasennaEdText.text, Toast.LENGTH_LONG).show()
                    errorText.text = getString(R.string.clave_administrador_erronea)
                    false
                }else{
                    lifecycleScope.launch {
                        var users : List<UsuarioEntity>
                        withContext(Dispatchers.IO){
                            val nuevoUsuario = UsuarioEntity(
                                0,
                                usuarioEdText.text.toString().trim(),
                                contrasennaEdText.text.toString().trim(),
                                esHombre,
                                rolUsuario
                            )

                            // Insertar el usuario utilizando el UserDao
                            userDao.insertUser(nuevoUsuario)
                            users = userDao.getAllUsers()
                        }


                        runOnUiThread {
                            Log.i("Usuarios", "$users")
                        }
                    }

                    true
                }
            }
        }
    }
}