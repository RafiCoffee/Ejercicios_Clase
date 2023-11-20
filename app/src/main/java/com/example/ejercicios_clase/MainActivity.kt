package com.example.ejercicios_clase

import android.os.Bundle
import android.view.ViewPropertyAnimator
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var prueba: RelativeLayout
    private lateinit var pruebaBt: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dado_activity)

        prueba = findViewById(R.id.prueba)
        pruebaBt = findViewById(R.id.pruebaBt)

        pruebaBt.setOnClickListener {
            val animator: ViewPropertyAnimator = prueba.animate().rotationX(99999999999999f).rotationY(99999999999999f).translationY(120f).setDuration(500)
            prueba.animate().rotationX(-99999999999999f).rotationY(-99999999999999f).translationY(-300f).setDuration(500)
            animator.start()
        }
    }
}