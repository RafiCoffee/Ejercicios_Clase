package com.example.ejercicios_clase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ejercicios_clase.object_models.Estadisticas
import com.example.ejercicios_clase.object_models.Repositorio
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity: AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        iniciarNav()
    }

    private fun iniciarNav(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        navHostFragment.let {
            // El fragmento no es nulo, realiza tus operaciones aquí
            navController = it.navController
            val appBarConfiguration = AppBarConfiguration(navController.graph)

            // Configura la barra de acción con el controlador de navegación
            setupActionBarWithNavController(navController, appBarConfiguration)
            bottomNav.setupWithNavController(navController)
        } ?: run {}

        bottomNav.setOnItemSelectedListener { menuItem ->
            val currentDestination = navController.currentDestination

            val hasTitleArgument = currentDestination?.arguments?.containsKey("nombreFragmento") == true
            val hasStatsArgument = currentDestination?.arguments?.containsKey("stats") == true

            if (hasTitleArgument && hasStatsArgument) {
                val stats: IntArray = intArrayOf(Estadisticas.totalJuegos, Estadisticas.totalJuegosAgregados, Estadisticas.totalJuegosEliminados, Estadisticas.totalJuegosEditados)
                val bundle = bundleOf("nombreFragmento" to "Bienvenido al fragmento " + menuItem.title.toString().lowercase(), "stats" to stats)
                navController.navigate(menuItem.itemId, bundle)
                true
            } else if(hasTitleArgument){
                val bundle = bundleOf("nombreFragmento" to "Bienvenido al fragmento " + menuItem.title.toString().lowercase())
                navController.navigate(menuItem.itemId, bundle)
                true
            }else{
                // Si ya tiene argumentos, simplemente navega al destino sin agregar nuevos argumentos
                Toast.makeText(this, "Sin argumentos que pasar", Toast.LENGTH_LONG).show()
                navController.navigate(menuItem.itemId)
                true
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}