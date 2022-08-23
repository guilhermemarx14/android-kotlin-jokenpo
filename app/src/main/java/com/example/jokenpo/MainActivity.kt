package com.example.jokenpo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.jokenpo.databinding.ActivityMainBinding
import com.example.jokenpo.interfaces.PlayListener
import com.example.jokenpo.observers.ActivityObserver
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), PlayListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
    private lateinit var navDrawer: NavigationView
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var titleTextView: TextView
    var currentPlay = "Rock"
    override var thisContext: Context
        get() = applicationContext
        set(value) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        getSavedInstanceState(savedInstanceState)
        initToolbar()
        initNavigation()
        lifecycle.addObserver(ActivityObserver())
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initNavigation(){
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(setOf(R.id.playerFragment, R.id.resultFragment), drawer)

        navController.addOnDestinationChangedListener{
                _, destination, _ ->
            when(destination.id){
                R.id.homeFragment -> {
                    bottomNav.visibility = View.GONE
                    navDrawer.visibility = View.GONE
                    titleTextView.text = getString(R.string.app_name)
                }
                R.id.playerFragment -> {
                    bottomNav.visibility = View.VISIBLE
                    navDrawer.visibility = View.VISIBLE
                    titleTextView.text = getString(R.string.player_title)
                }
                R.id.resultFragment -> {
                    bottomNav.visibility = View.VISIBLE
                    navDrawer.visibility = View.VISIBLE
                    titleTextView.text = getString(R.string.result_title)
                }
            }
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navDrawer.setupWithNavController(navController)
        navDrawer.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.resultFragment -> {
                    val args = Bundle()
                    args.putString("currentPlay", currentPlay)
                    navController.navigate(it.itemId,args)
                }
                else -> navController.navigate(it.itemId)
            }
            true
        }
        bottomNav.setupWithNavController(navController)
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.resultFragment -> {
                    val args = Bundle()
                    args.putString("currentPlay", currentPlay)
                    navController.navigate(it.itemId,args)
                }
                else -> navController.navigate(it.itemId)
            }
            true
        }
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawer = binding.root
        navDrawer = binding.navigationViewInclude.navigationView
        bottomNav = binding.bottomBarInclude.bottomNav
        toolbar = binding.appBarInclude.appBar
        titleTextView = binding.appBarInclude.myToolbarTitle

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        outState.putString("myState", "MainActivity - The stateful component's states comes here.")
    }

    private fun getSavedInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.getString("myState")?.let {
            Log.d("Jokenpo",it)
        }

    }

    override fun onPlaySelected(selectedPlay: String) {
        currentPlay = selectedPlay
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        return
    }



}