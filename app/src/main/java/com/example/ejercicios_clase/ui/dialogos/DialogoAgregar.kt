package com.example.ejercicios_clase.ui.dialogos

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicios_clase.R
import com.example.ejercicios_clase.data.dataSource.mem.models.Videojuego
import com.example.ejercicios_clase.data.dialoges.DialogCallbackCalendario
import com.example.ejercicios_clase.data.retrofit.RetrofitModule
import com.example.ejercicios_clase.data.retrofit.requests.RequestAddVideojuego
import com.example.ejercicios_clase.ui.modelView.VideojuegosViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate

class DialogoAgregar(myRecyclerView: RecyclerView) {
    private val contexto = myRecyclerView.context
    private lateinit var view : View

    private lateinit var tituloVideojuego : EditText
    private lateinit var generoVideojuego : EditText
    private lateinit var notasVideojuego : RadioGroup
    private lateinit var notaVideojuego : RadioButton
    private lateinit var listaNotasVideojuego : MutableList<RadioButton>
    private lateinit var fechaVideojuego : Button
    private lateinit var imagenVideojuego : Spinner
    fun mostrarDialogoAgregarVideojuego(viewModel: VideojuegosViewModel, userToken: String){
        val inflater = LayoutInflater.from(contexto)
        view = inflater.inflate(R.layout.agregar_dialogo, null)

        asociarElementos()

        fechaVideojuego.text = LocalDate.now().toString().replace('-', '/')
        fechaVideojuego.setOnClickListener { elegirFecha() }

        val spinnerOption = arrayOf("Zelda: BOTW", "Zelda: TOTK", "Red Dead Redemption 1", "Red Dead Redemption 2", "No Man's Sky", "Call Of Duty: Black Ops 3", "Grand Theft Auto V", "Elden Ring", "Juego Generico")
        val adapter = ArrayAdapter(contexto, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spinnerOption)
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        imagenVideojuego.adapter = adapter

        val builder = AlertDialog.Builder(contexto)
        builder.setView(view)

        builder.setPositiveButton("Si") { _, _ ->
            val nuevoVideojuego = crearVideojuego()

            if(nuevoVideojuego != null){
                viewModel.viewModelScope.launch {
                    val response = RetrofitModule.apiService.addVideojuego(userToken, RequestAddVideojuego(nuevoVideojuego!!.titulo, nuevoVideojuego.genero, nuevoVideojuego.nota, nuevoVideojuego.fechaSalida, null))

                    if(response.isSuccessful && response.body()?.result.equals("ok insercion")){
                        nuevoVideojuego.id = response.body()?.insertId!!.toInt()

                        Toast.makeText(contexto, "Agregando " + nuevoVideojuego.titulo, Toast.LENGTH_LONG).show()
                        viewModel.agregarVideojuegoRepo(nuevoVideojuego)
                    }
                }
            }
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    fun asociarElementos(){
        tituloVideojuego = view.findViewById(R.id.tituloVideojuego)
        generoVideojuego = view.findViewById(R.id.generoVideojuego)
        notasVideojuego = view.findViewById(R.id.notasVideojuego)
        fechaVideojuego = view.findViewById(R.id.fechaVideojuegoBt)
        imagenVideojuego = view.findViewById(R.id.imagenVideojuego)

        listaNotasVideojuego = mutableListOf()
        listaNotasVideojuego.add(view.findViewById(R.id.notaVideojuego1))
        listaNotasVideojuego.add(view.findViewById(R.id.notaVideojuego2))
        listaNotasVideojuego.add(view.findViewById(R.id.notaVideojuego3))
        listaNotasVideojuego.add(view.findViewById(R.id.notaVideojuego4))
        listaNotasVideojuego.add(view.findViewById(R.id.notaVideojuego5))
    }

    fun crearVideojuego() : Videojuego? {
        val newVideojuego = Array(5){""}
        notaVideojuego = listaNotasVideojuego.firstOrNull{ it.isChecked }!!
        newVideojuego[0] = tituloVideojuego.text.toString()
        newVideojuego[1] = generoVideojuego.text.toString()
        newVideojuego[2] = notaVideojuego.text?.toString() ?: "0"
        newVideojuego[3] = fechaVideojuego.text.toString().replace('/', '-')
        val selectedImages = imagenVideojuego.selectedItem.toString()
        var image = 0

        when (selectedImages){
            "Zelda: BOTW" -> image = R.drawable.zelda_botw_portada
            "Zelda: TOTK" -> image = R.drawable.zelda_totk_portada
            "Red Dead Redemption 1" -> image = R.drawable.rdr1_portada
            "Red Dead Redemption 2" -> image = R.drawable.rdr2_portada
            "No Man's Sky" -> image = R.drawable.no_mans_sky_portada
            "Call Of Duty: Black Ops 3" -> image = R.drawable.call_of_duty_bo3_portada
            "Grand Theft Auto V" -> image = R.drawable.gta5_portada
            "Elden Ring" -> image = R.drawable.elden_ring_portada
            "Juego Generico" -> image = R.drawable.juego_generico_portada
        }

        newVideojuego[4] = image.toString()

        if(newVideojuego[0].isNotEmpty() && newVideojuego[1].isNotEmpty() && newVideojuego[3].isNotEmpty()){
            val videojuegoCreado = Videojuego(-1, newVideojuego[0], newVideojuego[1], newVideojuego[2].toInt(), newVideojuego[3].replace('-', '/'), null)
            return videojuegoCreado
        }else{
            Toast.makeText(contexto, "Error al crear, debes rellenar todos los campos", Toast.LENGTH_SHORT).show()
            return null
        }
    }

    private fun elegirFecha(){
        mostrarCalendario(object: DialogCallbackCalendario {
            override fun onDialogResult(fecha: String, isCanceled: Boolean) {
                if(!isCanceled){
                    fechaVideojuego.text = fecha
                }
            }
        })
    }

    fun mostrarCalendario(callback: DialogCallbackCalendario){
        var fechaIntroducida = ""
        val builder = AlertDialog.Builder(contexto)
        builder.setTitle("Seleccionar Fecha ")

        val layout = LinearLayout(contexto)

        val fechaSalida = DatePicker(contexto)
        layout.addView(fechaSalida)

        builder.setView(layout)

        builder.setPositiveButton("Aceptar") { _, _ ->

            val mes = (fechaSalida.month + 1).toString().padStart(2, '0')
            val dia = fechaSalida.dayOfMonth.toString().padStart(2,'0')
            val fecha = "${fechaSalida.year}/${mes}/${dia}"
            fechaIntroducida = fecha


            callback.onDialogResult(fechaIntroducida, false)
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            callback.onDialogResult(fechaIntroducida, true)
            dialog.cancel()
        }

        builder.show()
    }
}