package com.example.ejercicios_clase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicios_clase.controller.Controller
import com.example.ejercicios_clase.databinding.MainActivityBinding

class MainActivity: AppCompatActivity() {
    private lateinit var control: Controller
    lateinit var mainBinding: MainActivityBinding
    //lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = MainActivityBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        iniciar()
    }

    private fun iniciar() {
        initRecyclerView()
        control = Controller(this)
        control.setAdapter()
        control.loggOut()
    }

    private fun initRecyclerView() {
        var myRecyclerView = findViewById<RecyclerView>(R.id.my_recycler_view)
        myRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}