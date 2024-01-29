package com.example.ejercicios_clase.data.ui.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicios_clase.R
import com.example.ejercicios_clase.databinding.VideojuegosActivityBinding
import com.example.ejercicios_clase.data.ui.viewModel.VideojuegosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideojuegosFragment : Fragment() {

    private lateinit var videojuegosBinding: VideojuegosActivityBinding
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var viewModel: VideojuegosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        videojuegosBinding = VideojuegosActivityBinding.inflate(inflater, container, false)
        return videojuegosBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleArgumentValue = arguments?.getString("nombreFragmento")
        if (titleArgumentValue != null) {
            Toast.makeText(view.context, titleArgumentValue, Toast.LENGTH_LONG).show()
        }

        iniciar()
    }

    private fun iniciar() {
        iniciarRecyclerView()

        viewModel = ViewModelProvider(this).get(VideojuegosViewModel::class.java)
        viewModel.setAdapter()

        myRecyclerView = videojuegosBinding.myRecyclerView
        viewModel.setRecyclerView(myRecyclerView)

        val addButton = videojuegosBinding.root.findViewById<ImageButton>(R.id.btn_add)
        addButton.setOnClickListener { viewModel.crearVideojuego() }
    }

    private fun iniciarRecyclerView() {
        myRecyclerView = videojuegosBinding.myRecyclerView
        myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}
