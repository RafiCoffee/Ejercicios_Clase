package com.example.ejercicios_clase.ui.modelView

import android.util.Log
import android.widget.ImageButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicios_clase.data.dataSource.mem.models.Repositorio
import com.example.ejercicios_clase.data.dataSource.mem.models.Videojuego
import com.example.ejercicios_clase.domain.userCase.AgregarVideojuegoUserCase
import com.example.ejercicios_clase.domain.userCase.EditarVideojuegoUserCase
import com.example.ejercicios_clase.domain.userCase.EliminarVideojuegoUserCase
import com.example.ejercicios_clase.domain.userCase.GetVideojuegosNotaUserCase
import com.example.ejercicios_clase.domain.userCase.GetVideojuegosUserCase
import com.example.ejercicios_clase.data.ui.adapter.AdapterVideojuego
import com.example.ejercicios_clase.ui.dialogos.DialogoAgregar
import com.example.ejercicios_clase.ui.dialogos.DialogoEditar
import com.example.ejercicios_clase.ui.dialogos.DialogoEliminar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class VideojuegosViewModel @Inject constructor() : ViewModel(){
    var videojuegosListLiveData : MutableLiveData<List<Videojuego>> = MutableLiveData()
    var progressBarLiveData = MutableLiveData<Boolean>()
    var busqueda = MutableLiveData<Int>()

    lateinit var useCaseList : GetVideojuegosUserCase
    lateinit var useCaseNotaList : GetVideojuegosNotaUserCase
    lateinit var useCaseAgregarVideojuego : AgregarVideojuegoUserCase
    lateinit var useCaseEditarVideojuego : EditarVideojuegoUserCase
    lateinit var useCaseEliminarVideojuego : EliminarVideojuegoUserCase

    lateinit var myRecyclerView: RecyclerView

    lateinit var agregarVideojuego: DialogoAgregar
    lateinit var editarVideojuego: DialogoEditar
    lateinit var eliminarVideojuego: DialogoEliminar

    fun setAdapter(recyclerView: RecyclerView) {
        recyclerView.adapter = AdapterVideojuego(
            videojuegosListLiveData.value as MutableList<Videojuego>,
            { pos -> eliminarVideoJuego(pos) },
            { pos -> editarVideojuego(pos) }
        )

        myRecyclerView = recyclerView

        agregarVideojuego = DialogoAgregar(recyclerView)
        editarVideojuego = DialogoEditar(recyclerView)
        eliminarVideojuego = DialogoEliminar(recyclerView)
    }

    fun buscarPorNota(nota : Int){
        busqueda.value = nota
    }

    fun lista(){
        useCaseList = GetVideojuegosUserCase()
        viewModelScope.launch {
            progressBarLiveData.value = true
            var data : List<Videojuego> = useCaseList.invoke()
            data.let {
                videojuegosListLiveData.value = it
                progressBarLiveData.value = false
            }
        }
        Log.i("TAG-VIDEOJUEGOS", "Lista Actualizada --- " + videojuegosListLiveData.value?.size)
    }

    fun listaPorNota(nota: Int){
        viewModelScope.launch {
            progressBarLiveData.value = true
            useCaseNotaList = GetVideojuegosNotaUserCase(nota)
            var data : List<Videojuego> = useCaseList()
            data.let {
                videojuegosListLiveData.value = it
                progressBarLiveData.value = false
            }
        }
    }

    fun setAddButton(addButton : ImageButton){
        addButton.setOnClickListener {
            agregarVideojuego.mostrarDialogoAgregarVideojuego(this)
        }
    }

    fun editarVideojuego(pos: Int){
        editarVideojuego.mostrarDialogoEditarVideojuego(pos, Repositorio.videojuegos[pos], this)
    }

    fun eliminarVideoJuego(pos: Int){
        eliminarVideojuego.mostrarDialogoEliminarVideojuego(pos, Repositorio.videojuegos[pos], this)
    }

    fun agregarVideojuegoRepo(videojuego: Videojuego){
        viewModelScope.launch {
            progressBarLiveData.value = true
            delay(2000)

            useCaseAgregarVideojuego = AgregarVideojuegoUserCase()
            useCaseAgregarVideojuego.invoke(videojuego)

            lista()
            myRecyclerView.adapter?.notifyItemInserted(Repositorio.videojuegos.size - 1)
            myRecyclerView.adapter?.notifyDataSetChanged()

            var data : List<Videojuego> = useCaseList()
            data.let {
                videojuegosListLiveData.value = it
                progressBarLiveData.value = false
            }
        }
    }
    fun editarVideojuegoRepo(pos: Int, videojuego: Videojuego){
        viewModelScope.launch {
            progressBarLiveData.value = true
            delay(2000)

            useCaseEditarVideojuego = EditarVideojuegoUserCase()
            useCaseEditarVideojuego.invoke(pos, videojuego)

            lista()
            myRecyclerView.adapter?.notifyItemChanged(pos)

            progressBarLiveData.value = false
        }
    }
    fun eliminarVideojuegoRepo(pos: Int){
        viewModelScope.launch {
            progressBarLiveData.value = true
            delay(2000)

            useCaseEliminarVideojuego = EliminarVideojuegoUserCase()
            useCaseEliminarVideojuego.invoke(pos)

            lista()
            Log.i("PRUEBA", "Actualizando lista --- " + Repositorio.videojuegos.size)
            myRecyclerView.adapter?.notifyItemRemoved(pos)
            myRecyclerView.adapter?.notifyItemRangeChanged(pos, Repositorio.videojuegos.size)

            progressBarLiveData.value = false
        }
    }
}