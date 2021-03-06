package com.smqpro.projectexample.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.smqpro.projectexample.R
import com.smqpro.projectexample.databinding.ActivityMainBinding
import com.smqpro.projectexample.model.AppDatabase
import com.smqpro.projectexample.model.repository.ProductRepositoryMockImpl

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val dao by lazy { AppDatabase.getDatabase(applicationContext).productDao() }
    private val repo by lazy { ProductRepositoryMockImpl(dao) }
    val mainViewModel by viewModels<MainViewModel> { MainViewModelProviderFactory(repo) }
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUi()
    }

    private fun setUi() {
        setViews()
    }

    private fun setViews() = binding?.apply {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph_main)
        val navController = navHostFragment.navController
        navController.graph = navGraph
        NavigationUI.setupActionBarWithNavController(this@MainActivity, navController);

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host).navigateUp()
    }

}