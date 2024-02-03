package com.example.ejercicios_clase.data.ui.views.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ejercicios_clase.ListaUsuarios
import com.example.ejercicios_clase.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PerfilFragment: Fragment() {
    private lateinit var view: View
    private lateinit var fotoPerfil: ImageView
    private lateinit var nombrePerfil: TextView
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

        asociarElementos()

        for(i in 0 until ListaUsuarios.obtenerUsuarios().size){
            if(ListaUsuarios.obtenerUsuarios().get(i).usuarioIniciado){
                if(!ListaUsuarios.obtenerUsuarios().get(i).sexo){
                    fotoPerfil.setImageResource(R.drawable.usuario_mujer_logo)
                }else{
                    fotoPerfil.setImageResource(R.drawable.usuario_hombre_logo)
                }
                nombrePerfil.text = ListaUsuarios.obtenerUsuarios().get(i).nombreUsuario
            }
        }
    }

    private fun asociarElementos(){
        fotoPerfil = view.findViewById(R.id.fotoPerfil)
        nombrePerfil = view.findViewById(R.id.nombrePerfil)
    }
}