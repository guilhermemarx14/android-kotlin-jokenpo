package com.example.jokenpo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jokenpo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureBinding()
        configureListeners()
    }

    private fun configureBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun configureListeners(){
        binding.playButton.setOnClickListener { goToPlayerActivity() }
    }

    private fun goToPlayerActivity(){
        val newIntent = Intent(this, PlayerActivity::class.java)
        startActivity(newIntent)
    }
}