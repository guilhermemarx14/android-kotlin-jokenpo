package com.example.jokenpo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.jokenpo.databinding.ActivityPlayerBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class PlayerActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout
    private lateinit var binding: ActivityPlayerBinding
    private lateinit var navDrawer: NavigationView
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        configureBinding()
        setupToolbar()
        configureListeners()
    }

    private fun goToResultActivity(){
        val newIntent = Intent(this, ResultActivity::class.java)
        startActivity(newIntent)
    }

    private fun goToMainActivity(){
        val newIntent = Intent(this, MainActivity::class.java)
        newIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(newIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_restart -> {
                goToMainActivity()
                true
            }
            else -> false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        drawer.openDrawer(GravityCompat.START)
        return true
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.appBarIncludePlayer.appBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        binding.appBarIncludePlayer.myToolbarTitle.text = getString(R.string.player_title)
    }

    private fun configureBinding() {
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        drawer = binding.root
        setContentView(drawer)

        navDrawer = binding.navigationViewIncludePlayer.navigationView
        bottomNav = binding.bottomBarIncludePlayer.bottomNav
    }

    private fun configureListeners() {

        navDrawer.setNavigationItemSelectedListener { menuItem ->
            drawer.closeDrawers()
            when (menuItem.itemId) {
                R.id.menu_option_result -> {
                    goToResultActivity()
                    true
                }
                R.id.menu_option_player -> {
                    true
                }
                else -> false
            }
        }

        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_bottom_option_player -> {
                    true
                }
                R.id.menu_bottom_option_result -> {
                    goToResultActivity()
                    true
                }
                else -> false
            }
        }
    }
}