<<<<<<<< HEAD:app/src/main/java/com/example/ejercicios_clase/data/ui/adapter/ViewHolderVideojuego.kt
package com.example.ejercicios_clase.data.ui.adapter
========
package com.example.ejercicios_clase.ui.adapter
>>>>>>>> origin/Hilt_Y_MvvM:app/src/main/java/com/example/ejercicios_clase/ui/adapter/ViewHolderVideojuego.kt

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicios_clase.R
import com.example.ejercicios_clase.databinding.ItemLayoutBinding
<<<<<<<< HEAD:app/src/main/java/com/example/ejercicios_clase/data/ui/adapter/ViewHolderVideojuego.kt
import com.example.ejercicios_clase.data.models.Videojuego
========
import com.example.ejercicios_clase.data.dataSource.mem.models.Videojuego
>>>>>>>> origin/Hilt_Y_MvvM:app/src/main/java/com/example/ejercicios_clase/ui/adapter/ViewHolderVideojuego.kt

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
            }
            binding.fechaSalidaVideojuego.text = videojuego.fechaSalida
            binding.imagenVideojuego.setImageResource(videojuego.image)

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