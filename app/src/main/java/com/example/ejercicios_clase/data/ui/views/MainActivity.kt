package com.example.ejercicios_clase.data.ui.views

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.ejercicios_clase.R
import com.example.ejercicios_clase.data.object_models.Estadisticas
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    private lateinit var cerrarSesionBt: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        iniciarNav()
        iniciarBarraSuperiorYLateral()

        cerrarSesionBt = drawerLayout.findViewById(R.id.cerrarSesionBt)

        cerrarSesionBt.setOnClickListener {
            val intentIniciarSesionActivity = Intent(this, InicioSesionActivity :: class.java)

            try{
                startActivity(intentIniciarSesionActivity)
            }catch (e : ActivityNotFoundException){
                Toast.makeText(this, "Error al acceder a la pantalla", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun iniciarNav(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        navHostFragment.let {
            // El fragmento no es nulo, realiza tus operaciones aquí
            navController = it.navController

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

    private fun iniciarBarraSuperiorYLateral(){
        drawerLayout = findViewById(R.id.drawerLayout)
        navView = findViewById(R.id.barraLateralOpciones)

        val toolbar = findViewById<Toolbar>(R.id.barraSuperior)
        setSupportActionBar(toolbar)

        // Configurar el icono de la barra de acción para abrir el menú lateral
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Configurar la acción cuando se selecciona un elemento del menú
        navView.setNavigationItemSelectedListener { opcion ->
            // Acciones según el elemento seleccionado
            // Por ejemplo, puedes cambiar de fragmento o actividad aquí

            /*when (opcion.itemId){
                R.id.menu_option1 -> {
                    val intentPerfilActivity = Intent(this, PerfilActivity::class.java)
                    try {
                        startActivity(intentPerfilActivity)
                    }catch (e : ActivityNotFoundException){
                        Toast.makeText(this, "Error al acceder a la pantalla", Toast.LENGTH_SHORT).show()
                    }
                }
            }*/

            // Cerrar el menú lateral
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}