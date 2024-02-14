package com.example.ejercicios_clase.ui.views.fragmentos

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ejercicios_clase.ListaUsuarios
import com.example.ejercicios_clase.R

class PerfilFragment: Fragment() {
    private lateinit var view: View
    private lateinit var fotoPerfil: ImageView
    private lateinit var nombrePerfil: TextView

    private lateinit var sPSesion : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_perfil, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleArgumentValue = arguments?.getString("nombreFragmento")
        if (titleArgumentValue != null) {
            Toast.makeText(view.context, titleArgumentValue, Toast.LENGTH_LONG).show()
        }

        sPSesion = requireActivity().getSharedPreferences("Sesion", AppCompatActivity.MODE_PRIVATE)
        asociarElementos()

        nombrePerfil.text = sPSesion.getString("Usuario", "Nulo")
    }

    private fun asociarElementos(){
        fotoPerfil = view.findViewById(R.id.fotoPerfil)
        nombrePerfil = view.findViewById(R.id.nombrePerfil)
    }
}