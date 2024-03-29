package com.example.ejercicios_clase.data.ui.views

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.PasswordTransformationMethod
import android.util.Base64
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicios_clase.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.lifecycleScope
import com.example.ejercicios_clase.data.dataSource.dataBase.dao.UsuarioDao
import com.example.ejercicios_clase.data.dataSource.dataBase.entities.UsuarioEntity
import com.example.ejercicios_clase.data.retrofit.RetrofitModule
import com.example.ejercicios_clase.data.retrofit.requests.RequestRegisterUsuario
import com.example.ejercicios_clase.ui.views.InicioSesionActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@AndroidEntryPoint
class RegistrarUsuarioActivity: AppCompatActivity() {
    private lateinit var volverBt: ImageButton
    private lateinit var usuarioEdText: EditText
    private lateinit var emailEdText: EditText
    private lateinit var confirmarEmailEdText: EditText
    private lateinit var contrasennaEdText: EditText
    private lateinit var confirmarContrasennaEdText: EditText
    private lateinit var condiciones: CheckBox
    private lateinit var errorText: TextView
    private lateinit var registrarUsuarioBt: Button
    private lateinit var imagViewFoto: ImageView
    private lateinit var imgViewFotoBt: Button

    private lateinit var startForResult: ActivityResultLauncher<Intent>
    private lateinit var startForGallery: ActivityResultLauncher<Intent>

    private var bitMap: Bitmap? = null

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
        usuarioEdText = findViewById(R.id.usuarioEditText)
        emailEdText = findViewById(R.id.emailEditText)
        confirmarEmailEdText = findViewById(R.id.confirmarEmailEditText)
        contrasennaEdText = findViewById(R.id.contrasennaEditText)
        contrasennaEdText.transformationMethod = PasswordTransformationMethod()
        confirmarContrasennaEdText = findViewById(R.id.confirmarContrasennaEditText)
        confirmarContrasennaEdText.transformationMethod = PasswordTransformationMethod()
        condiciones = findViewById(R.id.aceptarCondiciones)
        errorText = findViewById(R.id.errorText)
        registrarUsuarioBt = findViewById(R.id.registrarUsuarioBt)
        imagViewFoto = findViewById(R.id.imagen_usuario)
        imgViewFotoBt = findViewById(R.id.imagen_usuarioBt)
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

        volverBt.setOnClickListener {
            try{
                startActivity(intentIniciarSesionActivity)
            }catch (e : ActivityNotFoundException){
                Toast.makeText(this, "Error al acceder a la pantalla", Toast.LENGTH_SHORT).show()
            }
        }

        startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleImageCaptureResult(result)
        }

        startForGallery =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleImageGalleryResult(result)
        }

        imgViewFotoBt.setOnClickListener {
            val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startForGallery.launch(pickPhoto)
        }
    }

    private fun comprobarRegistro(): Boolean{
        val username = usuarioEdText.text.toString()
        val email = emailEdText.text.toString()
        val password = contrasennaEdText.text.toString()

        if(username.isEmpty() || email.isEmpty() || confirmarEmailEdText.text.toString().isEmpty() || password.isEmpty() || confirmarContrasennaEdText.text.toString().isEmpty()){
            errorText.text = getString(R.string.existe_campo_vacio)
            return false
        }else if(email.trim() != confirmarEmailEdText.text.toString().trim()){
            errorText.text = getString(R.string.emails_no_coinciden)
            return false
        }else if(password.trim() != confirmarContrasennaEdText.text.toString().trim()){
            errorText.text = getString(R.string.claves_no_coinciden)
            return false
        }else{
            if(!condiciones.isChecked){
                errorText.text = getString(R.string.debes_aceptar_condiciones)
                return false
            }else{
                lifecycleScope.launch(Dispatchers.IO){
                    val imagenBase64 = bitMap?.let { bitmapToBase64(it) }
                    val response = withContext(Dispatchers.IO){
                        RetrofitModule.apiService.registro(
                            RequestRegisterUsuario(
                                email,
                                password,
                                username,
                                imagenBase64
                            )
                        )
                    }

                    if(response.isSuccessful && response.body()?.result.equals("ok")){
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@RegistrarUsuarioActivity, "Usuario registrado con exito", Toast.LENGTH_LONG).show()
                            insertarUsuarioRoom(response.body()!!.insertId, email, password, username)
                        }
                    }else{
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@RegistrarUsuarioActivity, "No ha sido posible realizar el registro", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                return true
            }
        }
    }

    suspend fun insertarUsuarioRoom(idNuevoUsuario: Int, username: String, email: String, password: String){
        val nuevoUsuario = UsuarioEntity(
            idNuevoUsuario,
            email,
            password,
            username,
            1
        )

        userDao.insertUser(nuevoUsuario)
    }

    private val REQUEST_CAMERA_PERMISSION = 100
    private val REQUEST_GALLERY_PERMISSION = 102

    private fun handleImageCaptureResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            data?.let {
                val imageBitmap = it.extras?.get("data") as Bitmap
                imagViewFoto.setImageBitmap(imageBitmap)
                bitMap = imageBitmap
            }
        }
    }

    private fun handleImageGalleryResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            data?.let {
                val imageUri: Uri? = it.data
                val imageBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                imagViewFoto.setImageBitmap(imageBitmap)
                bitMap = imageBitmap
            }
        }
    }

    private fun bitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        val imagenbase64 =Base64.encodeToString(byteArray, Base64.DEFAULT)
        return "data:image/PNG;base64,$imagenbase64"

    }
}