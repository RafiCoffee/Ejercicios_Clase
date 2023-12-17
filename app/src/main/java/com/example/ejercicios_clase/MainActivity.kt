package com.example.ejercicios_clase

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicios_clase.controller.Controller
import com.example.ejercicios_clase.databinding.MainActivityBinding

class MainActivity: AppCompatActivity() {
    private lateinit var control: Controller
    lateinit var mainBinding: MainActivityBinding
    lateinit var recyclerView: RecyclerView
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

        recyclerView = findViewById(R.id.my_recycler_view)
        control.setRecyclerView(recyclerView)

        val addButton = findViewById<ImageButton>(R.id.btn_add)
        control.setAddButton(addButton)
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.my_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}