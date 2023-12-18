package com.example.ejercicios_clase.controller

import com.example.ejercicios_clase.R
import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicios_clase.MainActivity
import com.example.ejercicios_clase.adapter.AdapterVideojuego
import com.example.ejercicios_clase.dao.DaoVideojuegos
import com.example.ejercicios_clase.models.Videojuego
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

class Controller(val contexto: Context) {
    private lateinit var listVideojuegos: MutableList<Videojuego>
    private lateinit var adapterVideojuegos: AdapterVideojuego
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: ImageButton
    private lateinit var fechaSalidaBt: Button

    init {
        initData()
    }

    private fun initData() {
        listVideojuegos = DaoVideojuegos.mydao.getDataVideojuegos().toMutableList()
    }

    fun loggOut() {
        Toast.makeText(contexto, "Datos mostrados por pantalla", Toast.LENGTH_LONG).show()
        listVideojuegos.forEach {
            println(it)
        }
    }

    fun setAdapter() {
        val myActivity = contexto as MainActivity
        adapterVideojuegos = AdapterVideojuego(
            listVideojuegos,
            { pos ->
                eliminarVideojuego(pos)
            },
            { pos ->
                actualizarVideojuego(pos)
            }
        )
        myActivity.mainBinding.myRecyclerView.adapter = adapterVideojuegos
    }

    fun setRecyclerView(recyclerView: RecyclerView){
        this.recyclerView = recyclerView
    }

    fun setAddButton(addButton: ImageButton){
        this.addButton = addButton
        this.addButton.setOnClickListener{crearVideojuego()}
    }

    private fun crearVideojuego(){
        mostrarCrearDialogo(object: DialogCallbackC{
            override fun onDialogResult(newVideojuego: Array<String>, isCanceled: Boolean){
                if(!isCanceled){
                    val VideojuegoCreado = Videojuego(newVideojuego[0], newVideojuego[1], newVideojuego[2].toInt(), LocalDate.parse(newVideojuego[3], DateTimeFormatter.ISO_LOCAL_DATE), newVideojuego[4].toInt())
                    listVideojuegos.add(VideojuegoCreado)
                    Toast.makeText(contexto, VideojuegoCreado.titulo + "creado", Toast.LENGTH_LONG).show()

                    val newPos = (listVideojuegos.size-1)
                    adapterVideojuegos.notifyItemInserted(newPos)

                    recyclerView.smoothScrollToPosition(newPos)
                }
            }
        })
    }

    private fun actualizarVideojuego(pos: Int) {
        val videojuegoSeleccionado = listVideojuegos[pos]
        mostrarActualizarDialogo(videojuegoSeleccionado, object: DialogCallbackC{
            override fun onDialogResult(newVideojuego: Array<String>, isCanceled: Boolean){
                if(!isCanceled){
                    listVideojuegos.removeAt(pos)
                    val videojuegoActualizado = Videojuego(newVideojuego[0], newVideojuego[1], newVideojuego[2].toInt(), LocalDate.parse(newVideojuego[3], DateTimeFormatter.ISO_LOCAL_DATE), newVideojuego[4].toInt())
                    listVideojuegos.add(pos, videojuegoActualizado)
                    Toast.makeText(contexto, listVideojuegos.get(pos).titulo + " actualizado", Toast.LENGTH_LONG).show()
                    adapterVideojuegos.notifyItemChanged(pos)
                }
            }
        })
    }

    private fun eliminarVideojuego(pos: Int) {
        val builder = AlertDialog.Builder(contexto)
        builder.setTitle("Eliminar Videojuego")
        builder.setMessage("Deseas eliminar el " + listVideojuegos.get(pos).titulo)

        builder.setPositiveButton("Si") { dialog, which ->
            Toast.makeText(contexto, listVideojuegos.get(pos).titulo + " eliminado", Toast.LENGTH_LONG).show()
            listVideojuegos.removeAt(pos)

            adapterVideojuegos.notifyItemRemoved(pos)
            adapterVideojuegos.notifyItemRangeChanged(pos, listVideojuegos.size)
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }

    private fun elegirFecha(){
        mostrarCalendario(object: DialogCallbackD{
            override fun onDialogResult(fecha: String, isCanceled: Boolean) {
                if(!isCanceled){
                    fechaSalidaBt.text = fecha
                }
            }
        })
    }

    private fun mostrarActualizarDialogo(videojuegoSeleccionado: Videojuego, callback: DialogCallbackC){
        val newVideojuego = Array(5){""}

        var isCanceled = false
        val builder = AlertDialog.Builder(contexto)
        builder.setTitle("Actualizar Videojuego ")

        val layout = LinearLayout(contexto)
        layout.orientation = LinearLayout.VERTICAL

        val tituloTxt = TextView(contexto)
        tituloTxt.text = "Nombre: "
        layout.addView(tituloTxt)
        val titulo = EditText(contexto)
        titulo.setText(videojuegoSeleccionado.titulo)
        layout.addView(titulo)

        val generoTxt = TextView(contexto)
        generoTxt.text = "Género: "
        layout.addView(generoTxt)
        val genero = EditText(contexto)
        genero.setText(videojuegoSeleccionado.genero)
        layout.addView(genero)

        val notaTxt = TextView(contexto)
        notaTxt.text = "Nota: "
        layout.addView(notaTxt)
        val seleccionNota = LinearLayout(contexto)
        layout.addView(seleccionNota)
        val notas = RadioGroup(contexto)
        notas.orientation = RadioGroup.HORIZONTAL
        seleccionNota.addView(notas)
        var listaRadioBt = mutableListOf<RadioButton>()
        for (i in 0..5){
            val nota = RadioButton(contexto)
            nota.text = i.toString()
            notas.addView(nota)

            listaRadioBt.add(nota)
        }
        for (radioButton in listaRadioBt) {
            if (radioButton.text.toString().equals(videojuegoSeleccionado.nota.toString())) {
                radioButton.isChecked = true
                break
            }
        }

        val fechaSalidaTxt = TextView(contexto)
        fechaSalidaTxt.text = "Año De Salida: "
        layout.addView(fechaSalidaTxt)
        fechaSalidaBt = Button(contexto)
        val mes = videojuegoSeleccionado.fechaSalida.monthValue.toString().padStart(2, '0')
        val dia = videojuegoSeleccionado.fechaSalida.dayOfMonth.toString().padStart(2,'0')
        fechaSalidaBt.text = "${videojuegoSeleccionado.fechaSalida.year}-${mes}-${dia}"
        fechaSalidaBt.setOnClickListener { elegirFecha() }
        layout.addView(fechaSalidaBt)

        val ImagenTxt = TextView(contexto)
        ImagenTxt.text = "Imagen: "
        layout.addView(ImagenTxt)
        val spinnerOption = arrayOf("Zelda: BOTW", "Zelda: TOTK", "Red Dead Redemption 1", "Red Dead Redemption 2", "No Man's Sky", "Call Of Duty: Black Ops 3", "Grand Theft Auto V", "Elden Ring", "Juego Generico")
        val spinner = Spinner(contexto)
        val adapter = ArrayAdapter(contexto, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spinnerOption)
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = adapter
        var defaultSelection: String = ""
        for (i in 0 until spinnerOption.size){
            if(titulo.text.equals(spinnerOption.get(i))){
                defaultSelection = spinnerOption.get(i)
            }
        }
        spinner.setSelection(adapter.getPosition(defaultSelection))
        layout.addView(spinner)

        builder.setView(layout)

        builder.setPositiveButton("Aceptar"){ dialog, which ->

            val radioBtSeleccionado = listaRadioBt.firstOrNull{ it.isChecked }
            newVideojuego[0] = titulo.text.toString()
            newVideojuego[1] = genero.text.toString()
            newVideojuego[2] = radioBtSeleccionado?.text.toString()
            newVideojuego[3] = fechaSalidaBt.text.toString()
            val selectedImages = spinner.selectedItem.toString()
            var image: Int = 0

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

            if(!newVideojuego[0].isEmpty() && !newVideojuego[1].isEmpty() && !newVideojuego[2].isEmpty() && !newVideojuego[3].isEmpty()){
                callback.onDialogResult(newVideojuego, isCanceled)
            }else{
                Toast.makeText(contexto, "Error al modificar, debes rellenar todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancelar"){ dialog, which ->
            isCanceled = true
            callback.onDialogResult(newVideojuego, isCanceled)
            dialog.cancel()
        }

        builder.show()
    }

    private fun mostrarCrearDialogo(callback: DialogCallbackC){
        val newVideojuego = Array(5){""}
        var isCanceled = false
        val builder = AlertDialog.Builder(contexto)
        builder.setTitle("Añadir Videojuego ")

        val layout = LinearLayout(contexto)
        layout.orientation = LinearLayout.VERTICAL

        val tituloTxt = TextView(contexto)
        tituloTxt.text = "Título: "
        layout.addView(tituloTxt)
        val titulo = EditText(contexto)
        layout.addView(titulo)

        val generoTxt = TextView(contexto)
        generoTxt.text = "Genero: "
        layout.addView(generoTxt)
        val genero = EditText(contexto)
        layout.addView(genero)

        val notaTxt = TextView(contexto)
        notaTxt.text = "Nota: "
        layout.addView(notaTxt)
        val seleccionNota = LinearLayout(contexto)
        layout.addView(seleccionNota)
        val notas = RadioGroup(contexto)
        notas.orientation = RadioGroup.HORIZONTAL
        seleccionNota.addView(notas)
        var listaRadioBt = mutableListOf<RadioButton>()
        for (i in 0..5){
            val nota = RadioButton(contexto)
            nota.text = i.toString()
            notas.addView(nota)

            listaRadioBt.add(nota)
        }

        val fechaSalidaTxt = TextView(contexto)
        fechaSalidaTxt.text = "Año De Salida: "
        layout.addView(fechaSalidaTxt)
        fechaSalidaBt = Button(contexto)
        fechaSalidaBt.text = LocalDate.now().toString()
        fechaSalidaBt.setOnClickListener { elegirFecha() }
        layout.addView(fechaSalidaBt)

        val ImagenTxt = TextView(contexto)
        ImagenTxt.text = "Imagen: "
        layout.addView(ImagenTxt)
        val spinnerOption = arrayOf("Zelda: BOTW", "Zelda: TOTK", "Red Dead Redemption 1", "Red Dead Redemption 2", "No Man's Sky", "Call Of Duty: Black Ops 3", "Grand Theft Auto V", "Elden Ring", "Juego Generico")
        val spinner = Spinner(contexto)
        val adapter = ArrayAdapter(contexto, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spinnerOption)
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = adapter
        layout.addView(spinner)

        builder.setView(layout)

        builder.setPositiveButton("Aceptar"){ dialog, which ->
            
            val radioBtSeleccionado = listaRadioBt.firstOrNull{ it.isChecked }
            newVideojuego[0] = titulo.text.toString()
            newVideojuego[1] = genero.text.toString()
            newVideojuego[2] = radioBtSeleccionado?.text.toString()
            newVideojuego[3] = fechaSalidaBt.text.toString()
            val selectedImages = spinner.selectedItem.toString()
            var image: Int = 0

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

            if(!newVideojuego[0].isEmpty() && !newVideojuego[1].isEmpty() && !newVideojuego[2].isEmpty() && !newVideojuego[3].isEmpty()){
                callback.onDialogResult(newVideojuego, isCanceled)
            }else{
                Toast.makeText(contexto, "Error al crear, debes rellenar todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancelar"){ dialog, which ->
            isCanceled = true
            callback.onDialogResult(newVideojuego, isCanceled)
            dialog.cancel()
        }

        builder.show()
    }

    private fun mostrarCalendario(callback: DialogCallbackD) {
        var fechaIntroducida = ""
        var isCanceled = false
        val builder = AlertDialog.Builder(contexto)
        builder.setTitle("Seleccionar Fecha ")

        val layout = LinearLayout(contexto)

        val fechaSalida = DatePicker(contexto)
        layout.addView(fechaSalida)

        builder.setView(layout)

        builder.setPositiveButton("Aceptar") { dialog, which ->

            val mes = fechaSalida.month.toString().padStart(2, '0')
            val dia = fechaSalida.dayOfMonth.toString().padStart(2,'0')
            val fecha = "${fechaSalida.year}-${mes}-${dia}"
            fechaIntroducida = fecha


            callback.onDialogResult(fechaIntroducida, isCanceled)
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
            isCanceled = true
            callback.onDialogResult(fechaIntroducida, isCanceled)
            dialog.cancel()
        }

        builder.show()
    }

    interface DialogCallbackC{
        fun onDialogResult(newVideojuego: Array<String>, isCanceled: Boolean)
    }

    interface DialogCallbackD{
        fun onDialogResult(fecha: String, isCanceled: Boolean)
    }
}