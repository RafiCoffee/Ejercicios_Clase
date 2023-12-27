package com.example.ejercicios_clase.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicios_clase.R
import com.example.ejercicios_clase.controller.Controller
import com.example.ejercicios_clase.databinding.VideojuegosActivityBinding

class VideojuegosFragment: Fragment() {
    private lateinit var control: Controller
    lateinit var videojuegosBinding: VideojuegosActivityBinding
    private lateinit var myRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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
        control = Controller(requireContext(), videojuegosBinding)
        control.setAdapter()

        myRecyclerView = videojuegosBinding.myRecyclerView
        control.setRecyclerView(myRecyclerView)

        val addButton = videojuegosBinding.root.findViewById<ImageButton>(R.id.btn_add)
        control.setAddButton(addButton)
    }

    private fun iniciarRecyclerView() {
        myRecyclerView = videojuegosBinding.myRecyclerView
        myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}