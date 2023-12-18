package com.example.ejercicios_clase.controller

import com.example.ejercicios_clase.R
import android.app.AlertDialog
import android.content.Context
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
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicios_clase.MainActivity
import com.example.ejercicios_clase.adapter.AdapterVideojuego
import com.example.ejercicios_clase.dao.DaoVideojuegos
import com.example.ejercicios_clase.models.Videojuego
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

    /*fun loggOut() {
        Toast.makeText(contexto, "Datos mostrados por pantalla", Toast.LENGTH_LONG).show()
        listVideojuegos.forEach {
            println(it)
        }
    }*/

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
                    val videojuegoCreado = Videojuego(newVideojuego[0], newVideojuego[1], newVideojuego[2].toInt(), LocalDate.parse(newVideojuego[3], DateTimeFormatter.ISO_LOCAL_DATE).toString().replace('-','/'), newVideojuego[4].toInt())
                    listVideojuegos.add(videojuegoCreado)
                    Toast.makeText(contexto, videojuegoCreado.titulo + "creado", Toast.LENGTH_LONG).show()

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
                    val videojuegoActualizado = Videojuego(newVideojuego[0], newVideojuego[1], 4, newVideojuego[3].replace('-', '/'), newVideojuego[4].toInt())
                    listVideojuegos.add(pos, videojuegoActualizado)
                    Toast.makeText(contexto, listVideojuegos[pos].titulo + " actualizado", Toast.LENGTH_LONG).show()
                    adapterVideojuegos.notifyItemChanged(pos)
                }
            }
        })
    }

    private fun eliminarVideojuego(pos: Int) {
        val builder = AlertDialog.Builder(contexto)
        builder.setTitle("Eliminar Videojuego")
        builder.setMessage("Deseas eliminar el " + listVideojuegos[pos].titulo)

        builder.setPositiveButton("Si") { _, _ ->
            Toast.makeText(contexto, listVideojuegos[pos].titulo + " eliminado", Toast.LENGTH_LONG).show()
            listVideojuegos.removeAt(pos)

            adapterVideojuegos.notifyItemRemoved(pos)
            adapterVideojuegos.notifyItemRangeChanged(pos, listVideojuegos.size)
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
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

        val builder = AlertDialog.Builder(contexto)
        builder.setTitle("Actualizar Videojuego ")

        val layout = LinearLayout(contexto)
        layout.orientation = LinearLayout.VERTICAL

        val tituloTxt = TextView(contexto)
        tituloTxt.text = contexto.getString(R.string.titulo)
        layout.addView(tituloTxt)
        val titulo = EditText(contexto)
        titulo.setText(videojuegoSeleccionado.titulo)
        layout.addView(titulo)

        val generoTxt = TextView(contexto)
        generoTxt.text = contexto.getString(R.string.genero)
        layout.addView(generoTxt)
        val genero = EditText(contexto)
        genero.setText(videojuegoSeleccionado.genero)
        layout.addView(genero)

        val notaTxt = TextView(contexto)
        notaTxt.text = contexto.getText(R.string.nota)
        layout.addView(notaTxt)
        val seleccionNota = LinearLayout(contexto)
        layout.addView(seleccionNota)
        val notas = RadioGroup(contexto)
        notas.orientation = RadioGroup.HORIZONTAL
        seleccionNota.addView(notas)
        val listaRadioBt = mutableListOf<RadioButton>()
        for (i in 0..5){
            val nota = RadioButton(contexto)
            nota.text = i.toString()
            notas.addView(nota)

            listaRadioBt.add(nota)
        }
        for (radioButton in listaRadioBt) {
            if (radioButton.text.toString() == videojuegoSeleccionado.nota.toString()) {
                radioButton.isChecked = true
                break
            }
        }

        val fechaSalidaTxt = TextView(contexto)
        fechaSalidaTxt.text = contexto.getText(R.string.fecha_salida)
        layout.addView(fechaSalidaTxt)
        fechaSalidaBt = Button(contexto)
        fechaSalidaBt.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        fechaSalidaBt.setPadding(10)
        val fecha = LocalDate.parse(videojuegoSeleccionado.fechaSalida.replace('/', '-'), DateTimeFormatter.ISO_LOCAL_DATE)
        val mes = fecha.monthValue.toString().padStart(2, '0')
        val dia = fecha.dayOfMonth.toString().padStart(2,'0')
        val fechaCompleta = "${fecha.year}/${mes}/${dia}"
        fechaSalidaBt.text = fechaCompleta
        fechaSalidaBt.setOnClickListener { elegirFecha() }
        layout.addView(fechaSalidaBt)

        val imagenTxt = TextView(contexto)
        imagenTxt.text = contexto.getText(R.string.imagen)
        layout.addView(imagenTxt)
        val spinnerOption = arrayOf("Zelda: BOTW", "Zelda: TOTK", "Red Dead Redemption 1", "Red Dead Redemption 2", "No Man's Sky", "Call Of Duty: Black Ops 3", "Grand Theft Auto V", "Elden Ring", "Juego Generico")
        val spinner = Spinner(contexto)
        val adapter = ArrayAdapter(contexto, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spinnerOption)
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = adapter
        when (videojuegoSeleccionado.image){
            R.drawable.zelda_botw_portada -> spinner.setSelection(0)
            R.drawable.zelda_totk_portada -> spinner.setSelection(1)
            R.drawable.rdr1_portada -> spinner.setSelection(2)
            R.drawable.rdr2_portada -> spinner.setSelection(3)
            R.drawable.no_mans_sky_portada -> spinner.setSelection(4)
            R.drawable.call_of_duty_bo3_portada -> spinner.setSelection(5)
            R.drawable.gta5_portada -> spinner.setSelection(6)
            R.drawable.elden_ring_portada -> spinner.setSelection(7)
            R.drawable.juego_generico_portada -> spinner.setSelection(8)
        }
        layout.addView(spinner)

        builder.setView(layout)

        builder.setPositiveButton("Aceptar"){ _, _ ->

            val radioBtSeleccionado = listaRadioBt.firstOrNull{ it.isChecked }
            newVideojuego[0] = titulo.text.toString()
            newVideojuego[1] = genero.text.toString()
            newVideojuego[2] = radioBtSeleccionado?.text.toString()
            newVideojuego[3] = fechaSalidaBt.text.toString().replace('/', '-')
            val selectedImages = spinner.selectedItem.toString()
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

            if(newVideojuego[0].isNotEmpty() && newVideojuego[1].isNotEmpty() && newVideojuego[2].isNotEmpty() && newVideojuego[3].isNotEmpty()){
                callback.onDialogResult(newVideojuego, false)
            }else{
                Toast.makeText(contexto, "Error al modificar, debes rellenar todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancelar"){ dialog, _ ->
            callback.onDialogResult(newVideojuego, true)
            dialog.cancel()
        }

        builder.show()
    }

    private fun mostrarCrearDialogo(callback: DialogCallbackC){
        val newVideojuego = Array(5){""}

        val builder = AlertDialog.Builder(contexto)
        builder.setTitle("Añadir Videojuego ")

        val layout = LinearLayout(contexto)
        layout.orientation = LinearLayout.VERTICAL

        val tituloTxt = TextView(contexto)
        tituloTxt.text = contexto.getText(R.string.titulo)
        layout.addView(tituloTxt)
        val titulo = EditText(contexto)
        layout.addView(titulo)

        val generoTxt = TextView(contexto)
        generoTxt.text = contexto.getText(R.string.genero)
        layout.addView(generoTxt)
        val genero = EditText(contexto)
        layout.addView(genero)

        val notaTxt = TextView(contexto)
        notaTxt.text = contexto.getString(R.string.nota)
        layout.addView(notaTxt)
        val seleccionNota = LinearLayout(contexto)
        layout.addView(seleccionNota)
        val notas = RadioGroup(contexto)
        notas.orientation = RadioGroup.HORIZONTAL
        seleccionNota.addView(notas)
        val listaRadioBt = mutableListOf<RadioButton>()
        for (i in 0..5){
            val nota = RadioButton(contexto)
            nota.text = i.toString()
            notas.addView(nota)

            listaRadioBt.add(nota)
        }

        val fechaSalidaTxt = TextView(contexto)
        fechaSalidaTxt.text = contexto.getString(R.string.fecha_salida)
        layout.addView(fechaSalidaTxt)
        fechaSalidaBt = Button(contexto)
        fechaSalidaBt.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        fechaSalidaBt.setPadding(10)
        fechaSalidaBt.text = LocalDate.now().toString().replace('-', '/')
        fechaSalidaBt.setOnClickListener { elegirFecha() }
        layout.addView(fechaSalidaBt)

        val imagenTxt = TextView(contexto)
        imagenTxt.text = contexto.getString(R.string.imagen)
        layout.addView(imagenTxt)
        val spinnerOption = arrayOf("Zelda: BOTW", "Zelda: TOTK", "Red Dead Redemption 1", "Red Dead Redemption 2", "No Man's Sky", "Call Of Duty: Black Ops 3", "Grand Theft Auto V", "Elden Ring", "Juego Generico")
        val spinner = Spinner(contexto)
        val adapter = ArrayAdapter(contexto, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spinnerOption)
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = adapter
        layout.addView(spinner)

        builder.setView(layout)

        builder.setPositiveButton("Aceptar"){ _, _ ->
            
            val radioBtSeleccionado = listaRadioBt.firstOrNull{ it.isChecked }
            newVideojuego[0] = titulo.text.toString()
            newVideojuego[1] = genero.text.toString()
            newVideojuego[2] = radioBtSeleccionado?.text.toString()
            newVideojuego[3] = fechaSalidaBt.text.toString().replace('/', '-')
            val selectedImages = spinner.selectedItem.toString()
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

            if(newVideojuego[0].isNotEmpty() && newVideojuego[1].isNotEmpty() && newVideojuego[2].isNotEmpty() && newVideojuego[3].isNotEmpty()){
                callback.onDialogResult(newVideojuego, false)
            }else{
                Toast.makeText(contexto, "Error al crear, debes rellenar todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancelar"){ dialog, _ ->
            callback.onDialogResult(newVideojuego, true)
            dialog.cancel()
        }

        builder.show()
    }

    private fun mostrarCalendario(callback: DialogCallbackD) {
        var fechaIntroducida = ""
        val builder = AlertDialog.Builder(contexto)
        builder.setTitle("Seleccionar Fecha ")

        val layout = LinearLayout(contexto)

        val fechaSalida = DatePicker(contexto)
        layout.addView(fechaSalida)

        builder.setView(layout)

        builder.setPositiveButton("Aceptar") { _, _ ->

            val mes = fechaSalida.month.toString().padStart(2, '0')
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

    interface DialogCallbackC{
        fun onDialogResult(newVideojuego: Array<String>, isCanceled: Boolean)
    }

    interface DialogCallbackD{
        fun onDialogResult(fecha: String, isCanceled: Boolean)
    }
}