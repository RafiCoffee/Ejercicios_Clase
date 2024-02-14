package com.example.ejercicios_clase.ui.views.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ejercicios_clase.R

class EstadisticasFragment: Fragment() {
    private lateinit var view: View
    private lateinit var totalJuegos: TextView
    private lateinit var totalJuegosAgregados: TextView
    private lateinit var totalJuegosEliminados: TextView
    private lateinit var totalJuegosEditados: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_estadisticas, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        asociarElementos()

        val titleArgumentValue = arguments?.getString("nombreFragmento")
        if (titleArgumentValue != null) {
            Toast.makeText(view.context, titleArgumentValue, Toast.LENGTH_LONG).show()
        }

        val statsArgument = arguments?.getIntArray("stats")
        if(statsArgument != null){
            totalJuegos.text = statsArgument[0].toString()
            totalJuegosAgregados.text = statsArgument[1].toString()
            totalJuegosEliminados.text = statsArgument[2].toString()
            totalJuegosEditados.text = statsArgument[3].toString()
        }
    }

    private fun asociarElementos(){
        totalJuegos = view.findViewById(R.id.totalJuegos)
        totalJuegosAgregados = view.findViewById(R.id.totalJuegosAgregados)
        totalJuegosEliminados = view.findViewById(R.id.totalJuegosEliminados)
        totalJuegosEditados = view.findViewById(R.id.totalJuegosEditados)
    }
}