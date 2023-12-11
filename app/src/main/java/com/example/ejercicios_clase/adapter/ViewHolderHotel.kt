package com.example.ejercicios_clase.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ejercicios_clase.databinding.ItemLayoutBinding
import com.example.ejercicios_clase.models.Hotel

class ViewHolderHotel (view: View) : RecyclerView.ViewHolder (view) {
        private var binding: ItemLayoutBinding

        init {
            binding = ItemLayoutBinding.bind(view)
        }

        fun renderize(hotel: Hotel, /*position: Int*/) {
            binding.txtviewName.text = hotel.name
            binding.txtviewCity.text = hotel.city
            binding.txtviewProvince.text = hotel.province
            binding.txtviewPhone.text = hotel.phone

            Glide
                .with(itemView.context)
                .load(hotel.image)
                .centerCrop()
                .into(binding.ivHotel)

            //setOnClickListener(position)
        }

        /*private fun setOnClickListener(position: Int) {
            binding.btnEdit.setOnClickListener {
                updateOnClick(position)
            }
            binding.btnDelete.setOnClickListener {
                deleteOnClick(position)
            }
        }*/
}