package com.hamidreza.newsapp.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.hamidreza.newsapp.R
import com.hamidreza.newsapp.databinding.ActivityMainBinding
import com.hamidreza.newsapp.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private var isHome = false
    private lateinit var binding:ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i(TAG, "onCreate: ")

        nightMode()

        viewModel.isNight.observe(this){
            when(it){
                true -> {
                    AppCompatDelegate
                        .setDefaultNightMode(
                            AppCompatDelegate
                                .MODE_NIGHT_YES)
                }
                false -> {

                    AppCompatDelegate
                        .setDefaultNightMode(
                            AppCompatDelegate
                                .MODE_NIGHT_NO)
                }
            }
        }

        binding.apply {
            bottomNav.setupWithNavController(findNavController(R.id.nav_host))
            findNavController(R.id.nav_host)
                .addOnDestinationChangedListener { controller, destination, arguments ->
                    when (destination.id) {
                        R.id.splashFragment -> {
                            isHome = false
                            bottomNav.visibility = View.INVISIBLE
                            floatingActionButtonNight.visibility = View.INVISIBLE
                        }
                        R.id.introSliderFragment -> {
                            isHome = false
                            bottomNav.visibility = View.INVISIBLE
                            floatingActionButtonNight.visibility = View.INVISIBLE
                        }
                        R.id.signInFragment -> {
                            isHome = false
                            bottomNav.visibility = View.INVISIBLE
                            floatingActionButtonNight.visibility = View.INVISIBLE
                        }
                        R.id.homeFragment -> {
                            isHome = true
                            bottomNav.visibility = View.VISIBLE
                            floatingActionButtonNight.visibility = View.VISIBLE
                        }
                        R.id.savedFragment -> {
                            isHome = false
                            bottomNav.visibility = View.VISIBLE
                            floatingActionButtonNight.visibility = View.INVISIBLE
                        }
                        R.id.profileFragment -> {
                            isHome = false
                            bottomNav.visibility = View.VISIBLE
                            floatingActionButtonNight.visibility = View.INVISIBLE
                        }
                        R.id.articleFragment -> {
                            isHome =false
                            bottomNav.visibility = View.INVISIBLE
                            floatingActionButtonNight.visibility = View.INVISIBLE
                        }
                    }
                }
        }
    }


    fun nightMode() {
        binding.apply {
            floatingActionButtonNight.setOnClickListener {
                if (!viewModel.isNight.value!!){
                    viewModel.setNightMode(true)
                }else{
                    viewModel.setNightMode(false)
                }

            }
        }
    }

    override fun onBackPressed() {
        if (isHome) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}