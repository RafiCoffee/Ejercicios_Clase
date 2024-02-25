package com.example.ejercicios_clase.ui.dialogos

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicios_clase.R
import com.example.ejercicios_clase.data.dataSource.mem.models.Videojuego
import com.example.ejercicios_clase.ui.modelView.VideojuegosViewModel

class DialogoEliminar(myRecyclerView: RecyclerView) {
    private val contexto = myRecyclerView.context
    private lateinit var view : View

    private lateinit var mensajeEliminar : TextView
    fun mostrarDialogoEliminarVideojuego(pos: Int, videojuegoAEliminar: Videojuego, viewModel: VideojuegosViewModel){
        val inflater = LayoutInflater.from(contexto)
        view = inflater.inflate(R.layout.eliminar_dialogo, null)

        asociarElementos()

        mensajeEliminar.text = videojuegoAEliminar.titulo

        val builder = AlertDialog.Builder(contexto)
        builder.setView(view)

        builder.setPositiveButton("Si") { _, _ ->
            Toast.makeText(contexto, "Eliminando " + videojuegoAEliminar.titulo, Toast.LENGTH_LONG).show()
            viewModel.eliminarVideojuegoRepo(pos)
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    fun asociarElementos(){
        mensajeEliminar = view.findViewById(R.id.textoEliminar)
    }
}