package com.example.testbooking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.testbooking.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavController()
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeAsUpIndicator(com.example.core_ui.R.drawable.baseline_arrow_back_24)
    }

    private fun setupNavController() {
        with(findNavController(R.id.nav_host_fragment_activity_main)) {
            binding.toolbar.setupWithNavController(
                navController = this,
                configuration = AppBarConfiguration(this.graph)
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment_activity_main).navigateUp(
            AppBarConfiguration(findNavController(R.id.nav_host_fragment_activity_main).graph)
        )
    }
}