package com.example.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.splash.databinding.ActivityMainBinding
import com.example.splash.viewmodel.NotesViewModel

// add animation in fragments by using anim folder
// correct the delete icon in main ui.
// correct the last updated text
//id 'androidx.navigation.safeargs.kotlin'
// remove the deprecated delete menu in editNoteFragment
// remove the deprecated search menu in HomeNoteFragment
//correct the logo
// increase the size of searchbar and title of fragment action bar
// correct the toolbar of material tool bar in fragment
// add fading or ripple effect in own made custom toolbar icons
// add bold, italic, bullet points property in notes app
// add animation between fragments
// implement the delete functionality in edit note
// implement searchView in custom tool bar

class MainActivity : AppCompatActivity() {


    private lateinit var navController : NavController

    private lateinit var viewModel: NotesViewModel
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       supportActionBar?.hide()
        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NotesViewModel::class.java]


        // to hide system toolbar, just comment this 3 lines
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

}






