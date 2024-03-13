package com.example.ejercicios_clase.ui.views

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.ejercicios_clase.R
import com.example.ejercicios_clase.data.Estadisticas
import com.example.ejercicios_clase.data.dataSource.mem.models.Repositorio
import com.example.ejercicios_clase.data.retrofit.RetrofitModule
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var navController: NavController
    private lateinit var sPSesion: SharedPreferences

    private lateinit var cerrarSesionBt: Button
    private lateinit var nombreUsuario: TextView
    private lateinit var imgUsuario: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        sPSesion = getSharedPreferences("Sesion", MODE_PRIVATE)

        mantenerSesionIniciada()
        asociarElementos()

        iniciarNav()
        iniciarBarraSuperiorYLateral()
        cargarEventos()
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
            // Cerrar el menú lateral
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    fun asociarElementos(){
        cerrarSesionBt = findViewById(R.id.cerrarSesionBt)
        nombreUsuario = findViewById(R.id.nombreUsuario)
        imgUsuario = findViewById(R.id.fotoUsuarioImg)
        nombreUsuario.text = sPSesion.getString("Usuario", "Invitado")
    }

    fun cargarEventos(){
        cerrarSesionBt.setOnClickListener {
            cerrarSesion()

            val intentIniciarSesionActivity = Intent(this, InicioSesionActivity :: class.java)
            try{
                startActivity(intentIniciarSesionActivity)
            }catch (e : ActivityNotFoundException){
                Toast.makeText(this, "Error al acceder a la pantalla", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    fun mantenerSesionIniciada(){
        val sesionIniciada: SharedPreferences.Editor = sPSesion.edit()
        sesionIniciada.putBoolean("SesionIniciada", true)
        sesionIniciada.apply()
    }

    fun cerrarSesion(){
        val sesionIniciada: SharedPreferences.Editor = sPSesion.edit()
        sesionIniciada.putBoolean("SesionIniciada", false)
        sesionIniciada.apply()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}