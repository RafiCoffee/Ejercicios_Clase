package com.example.ejercicios_clase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicios_clase.R
import com.example.ejercicios_clase.models.Videojuego

class AdapterVideojuego (
    private var listaVideojuegos: MutableList<Videojuego>,
    private var deleteOnClick: (Int) -> Unit,
    private var updateOnClick: (Int) -> Unit
    ) : RecyclerView.Adapter<ViewHolderVideojuego>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderVideojuego {
            val layoutInflater = LayoutInflater.from(parent.context)
            val layoutItemVideojuego = R.layout.item_layout
            return ViewHolderVideojuego(
                layoutInflater.inflate(layoutItemVideojuego, parent, false), deleteOnClick, updateOnClick
            )
        }

        override fun onBindViewHolder(holder: ViewHolderVideojuego, position: Int) {
            holder.renderize(listaVideojuegos[position])
            holder.setOnClickListener(position)
        }

        override fun getItemCount(): Int = listaVideojuegos.size
}