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
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicios_clase.R
import com.example.ejercicios_clase.data.dataSource.mem.models.Videojuego
import com.example.ejercicios_clase.dialoges.DialogCallbackCalendario
import com.example.ejercicios_clase.ui.modelView.VideojuegosViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DialogoEditar(myRecyclerView: RecyclerView) {
    private val contexto = myRecyclerView.context
    private lateinit var view : View

    private lateinit var tituloVideojuegoEditado : EditText
    private lateinit var generoVideojuegoEditado : EditText
    private lateinit var notasVideojuegoEditado : RadioGroup
    private lateinit var notaVideojuegoEditado : RadioButton
    private lateinit var listaNotasVideojuegoEditado : MutableList<RadioButton>
    private lateinit var fechaVideojuegoEditado : Button
    private lateinit var imagenVideojuegoEditado : Spinner
    fun mostrarDialogoEditarVideojuego(pos: Int, videojuegoSeleccionado: Videojuego, viewModel: VideojuegosViewModel){
        val inflater = LayoutInflater.from(contexto)
        view = inflater.inflate(R.layout.editar_dialogo, null)

        asociarElementos()

        tituloVideojuegoEditado.setText(videojuegoSeleccionado.titulo)

        generoVideojuegoEditado.setText(videojuegoSeleccionado.genero)

        when (videojuegoSeleccionado.nota){
            1 -> listaNotasVideojuegoEditado[0].isChecked = true
            2 -> listaNotasVideojuegoEditado[1].isChecked = true
            3 -> listaNotasVideojuegoEditado[2].isChecked = true
            4 -> listaNotasVideojuegoEditado[3].isChecked = true
            5 -> listaNotasVideojuegoEditado[4].isChecked = true
        }

        val fecha = LocalDate.parse(videojuegoSeleccionado.fechaSalida.replace('/', '-'), DateTimeFormatter.ISO_LOCAL_DATE)
        val mes = fecha.monthValue.toString().padStart(2, '0')
        val dia = fecha.dayOfMonth.toString().padStart(2,'0')
        val fechaCompleta = "${fecha.year}/${mes}/${dia}"
        fechaVideojuegoEditado.text = fechaCompleta
        fechaVideojuegoEditado.setOnClickListener { elegirFecha() }

        val spinnerOption = arrayOf("Zelda: BOTW", "Zelda: TOTK", "Red Dead Redemption 1", "Red Dead Redemption 2", "No Man's Sky", "Call Of Duty: Black Ops 3", "Grand Theft Auto V", "Elden Ring", "Juego Generico")
        val adapter = ArrayAdapter(contexto, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spinnerOption)
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        imagenVideojuegoEditado.adapter = adapter
        when (videojuegoSeleccionado.image){
            R.drawable.zelda_botw_portada -> imagenVideojuegoEditado.setSelection(0)
            R.drawable.zelda_totk_portada -> imagenVideojuegoEditado.setSelection(1)
            R.drawable.rdr1_portada -> imagenVideojuegoEditado.setSelection(2)
            R.drawable.rdr2_portada -> imagenVideojuegoEditado.setSelection(3)
            R.drawable.no_mans_sky_portada -> imagenVideojuegoEditado.setSelection(4)
            R.drawable.call_of_duty_bo3_portada -> imagenVideojuegoEditado.setSelection(5)
            R.drawable.gta5_portada -> imagenVideojuegoEditado.setSelection(6)
            R.drawable.elden_ring_portada -> imagenVideojuegoEditado.setSelection(7)
            R.drawable.juego_generico_portada -> imagenVideojuegoEditado.setSelection(8)
        }

        val builder = AlertDialog.Builder(contexto)
        builder.setView(view)

        builder.setPositiveButton("Si") { _, _ ->
            val nuevoVideojuegoEditado = crearVideojuego()
            if(nuevoVideojuegoEditado != null){
                Toast.makeText(contexto, "Editando " + nuevoVideojuegoEditado.titulo, Toast.LENGTH_LONG).show()
                viewModel.editarVideojuegoRepo(pos, nuevoVideojuegoEditado)
            }
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    fun asociarElementos(){
        tituloVideojuegoEditado = view.findViewById(R.id.tituloVideojuego)
        generoVideojuegoEditado = view.findViewById(R.id.generoVideojuego)
        notasVideojuegoEditado = view.findViewById(R.id.notasVideojuego)
        fechaVideojuegoEditado = view.findViewById(R.id.fechaVideojuegoBt)
        imagenVideojuegoEditado = view.findViewById(R.id.imagenVideojuego)

        listaNotasVideojuegoEditado = mutableListOf()
        listaNotasVideojuegoEditado.add(view.findViewById(R.id.notaVideojuego1))
        listaNotasVideojuegoEditado.add(view.findViewById(R.id.notaVideojuego2))
        listaNotasVideojuegoEditado.add(view.findViewById(R.id.notaVideojuego3))
        listaNotasVideojuegoEditado.add(view.findViewById(R.id.notaVideojuego4))
        listaNotasVideojuegoEditado.add(view.findViewById(R.id.notaVideojuego5))
    }

    fun crearVideojuego() : Videojuego? {
        val newVideojuegoEditado = Array(5){""}
        notaVideojuegoEditado = listaNotasVideojuegoEditado.firstOrNull{ it.isChecked }!!
        newVideojuegoEditado[0] = tituloVideojuegoEditado.text.toString()
        newVideojuegoEditado[1] = generoVideojuegoEditado.text.toString()
        newVideojuegoEditado[2] = notaVideojuegoEditado.text?.toString() ?: "0"
        newVideojuegoEditado[3] = fechaVideojuegoEditado.text.toString().replace('/', '-')
        val selectedImages = imagenVideojuegoEditado.selectedItem.toString()
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

        newVideojuegoEditado[4] = image.toString()

        if(newVideojuegoEditado[0].isNotEmpty() && newVideojuegoEditado[1].isNotEmpty() && newVideojuegoEditado[3].isNotEmpty()){
            val videojuegoEditado = Videojuego(newVideojuegoEditado[0], newVideojuegoEditado[1], newVideojuegoEditado[2].toInt(), newVideojuegoEditado[3].replace('-', '/'), newVideojuegoEditado[4].toInt())
            return videojuegoEditado
        }else{
            Toast.makeText(contexto, "Error al modificar, debes rellenar todos los campos", Toast.LENGTH_SHORT).show()
            return null
        }
    }

    private fun elegirFecha(){
        mostrarCalendario(object: DialogCallbackCalendario {
            override fun onDialogResult(fecha: String, isCanceled: Boolean) {
                if(!isCanceled){
                    fechaVideojuegoEditado.text = fecha
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