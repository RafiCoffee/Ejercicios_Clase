package com.example.ejercicios_clase

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
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
import com.example.ejercicios_clase.models.Usuario

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

    private var esHombre: Boolean? = null
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
        }else if(contrasennaEdText.text.toString() != confirmarContrasennaEdText.text.toString()){
            errorText.text = getString(R.string.claves_no_coinciden)
            return false
        }else{
            if(!condiciones.isChecked){
                errorText.text = getString(R.string.debes_aceptar_condiciones)
                return false
            }else{
                return if(tipoUsuario.selectedItem.toString() == "Administrador" && !contrasennaEdText.text.equals("admin")){
                    errorText.text = getString(R.string.clave_administrador_erronea)
                    false
                }else{
                    if(esHombre == true){
                        ListaUsuarios.annadirUsuario(Usuario(usuarioEdText.text.toString(), contrasennaEdText.text.toString(), true, tipoUsuario.selectedItem.toString(), false))
                    }else{
                        ListaUsuarios.annadirUsuario(Usuario(usuarioEdText.text.toString(), contrasennaEdText.text.toString(), false, tipoUsuario.selectedItem.toString(),false))
                    }
                    true
                }
            }
        }
    }
}