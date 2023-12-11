package com.example.ejercicios_clase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicios_clase.R
import com.example.ejercicios_clase.models.Hotel

class AdapterHotel (
    private var listaHoteles: MutableList<Hotel>,
    var deleteOnClick: (Int) -> Unit,
    var updateOnClick: (Int) -> Unit
    ) : RecyclerView.Adapter<ViewHolderHotel>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHotel {
            val layoutInflater = LayoutInflater.from(parent.context)
            val layoutItemHotel = R.layout.item_layout
            return ViewHolderHotel(
                layoutInflater.inflate(layoutItemHotel, parent, false),
                deleteOnClick,
                updateOnClick
            )
        }

        override fun onBindViewHolder(holder: ViewHolderHotel, position: Int) {
            holder.renderize(listaHoteles[position], position)
        }

        override fun getItemCount(): Int = listaHoteles.size
}