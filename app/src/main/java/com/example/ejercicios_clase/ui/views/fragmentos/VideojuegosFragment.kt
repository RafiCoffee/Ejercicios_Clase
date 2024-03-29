package com.example.ejercicios_clase.ui.views.fragmentos

import android.content.Context.INPUT_METHOD_SERVICE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicios_clase.R
import com.example.ejercicios_clase.data.dataSource.mem.models.Repositorio
import com.example.ejercicios_clase.databinding.VideojuegosActivityBinding
import com.example.ejercicios_clase.ui.modelView.VideojuegosViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VideojuegosFragment: Fragment(), SearchView.OnQueryTextListener {
    lateinit var videojuegosBinding: VideojuegosActivityBinding
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var myProgressBar: ProgressBar
    private lateinit var sPSesion: SharedPreferences

    lateinit var userToken: String

    val videojuegosViewModel : VideojuegosViewModel by viewModels()

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

        sPSesion = view.context.getSharedPreferences("Sesion", AppCompatActivity.MODE_PRIVATE)
        userToken = sPSesion.getString("Token", "").toString()

        lifecycleScope.launch {
            Repositorio.cargarVideojuegos(userToken)

            iniciar()
        }
    }

    private fun iniciar() {
        iniciarRecyclerView()
        loadData()
        videojuegosViewModel.setAdapter(myRecyclerView)
        videojuegosViewModel.setToken(userToken)
        registerLiveData()

        val addButton = videojuegosBinding.root.findViewById<ImageButton>(R.id.btn_add)
        videojuegosViewModel.setAddButton(addButton)
    }

    private fun iniciarRecyclerView() {
        myRecyclerView = videojuegosBinding.myRecyclerView
        myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        myProgressBar = videojuegosBinding.barraCarga
    }

    private fun loadData(){
        videojuegosViewModel.lista()
    }

    private fun registerLiveData(){
        videojuegosViewModel.videojuegosListLiveData.observe(
            viewLifecycleOwner) { listaVideojuegos ->
            videojuegosViewModel.setAdapter(myRecyclerView)
            myRecyclerView.adapter?.notifyDataSetChanged()
        }

        videojuegosViewModel.progressBarLiveData.observe(
            viewLifecycleOwner
        ) { visible ->
            videojuegosBinding.barraCarga.isVisible = visible
            Log.i("TAG-VIDEOJUEGOS", "ProgressBar esta $visible")

            videojuegosViewModel.busqueda.observe(
                viewLifecycleOwner
            ) { nota ->
                videojuegosViewModel.listaPorNota(nota)
                hideKeyBoard()
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            videojuegosViewModel.buscarPorNota(query!!.toInt())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText.isNullOrEmpty()){
            videojuegosViewModel.lista()
            hideKeyBoard()
        }
        return true
    }

    private fun hideKeyBoard() {
        val imn = requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imn.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}