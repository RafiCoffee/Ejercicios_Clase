package com.example.ejercicios_clase.data.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicios_clase.R
import com.example.ejercicios_clase.databinding.ItemLayoutBinding
import com.example.ejercicios_clase.data.dataSource.mem.models.Videojuego

class ViewHolderVideojuego (view: View, var deleteOnClick: (Int) -> Unit, var updateOnClick: (Int) -> Unit) : RecyclerView.ViewHolder (view) {
        private var binding: ItemLayoutBinding

        init {
            binding = ItemLayoutBinding.bind(view)
        }

        fun renderize(videojuego: Videojuego) {
            binding.tituloVideojuego.text = videojuego.titulo
            binding.generoVideojuego.text = videojuego.genero
            when(videojuego.nota){
                0 -> binding.notaVideojuego.setImageResource(R.drawable.nota_0)
                1 -> binding.notaVideojuego.setImageResource(R.drawable.nota_1)
                2 -> binding.notaVideojuego.setImageResource(R.drawable.nota_2)
                3 -> binding.notaVideojuego.setImageResource(R.drawable.nota_3)
                4 -> binding.notaVideojuego.setImageResource(R.drawable.nota_4)
                5 -> binding.notaVideojuego.setImageResource(R.drawable.nota_5)
                else -> binding.notaVideojuego.setImageResource(R.drawable.nota_0)
            }
            binding.fechaSalidaVideojuego.text = videojuego.fechaSalida ?: "Sin fecha de salida"
            binding.imagenVideojuego.setImageResource(videojuego.image ?: R.drawable.juego_generico_portada)

            }

        fun setOnClickListener(position: Int) {
            binding.btEdit.setOnClickListener {
                updateOnClick(position)
            }
            binding.btDelete.setOnClickListener {
                deleteOnClick(position)
            }
        }
}