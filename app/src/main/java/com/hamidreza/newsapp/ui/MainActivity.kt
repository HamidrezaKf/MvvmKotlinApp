package com.hamidreza.newsapp.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.hamidreza.newsapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private var isHome = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate: ")
        bottom_nav.setupWithNavController(nav_host.findNavController())
        nav_host.findNavController()
            .addOnDestinationChangedListener { controller, destination, arguments ->
                when (destination.id) {
                    R.id.splashFragment -> {
                        isHome = false
                        bottom_nav.visibility = View.INVISIBLE
                    }
                    R.id.introSliderFragment -> {
                        isHome = false
                        bottom_nav.visibility = View.INVISIBLE
                    }
                    R.id.signInFragment -> {
                        isHome = false
                        bottom_nav.visibility = View.INVISIBLE
                    }
                    R.id.homeFragment -> {
                        isHome = true
                        bottom_nav.visibility = View.VISIBLE
                    }
                    R.id.savedFragment -> {
                        isHome = false
                        bottom_nav.visibility = View.VISIBLE
                    }
                    R.id.profileFragment -> {
                        isHome = false
                        bottom_nav.visibility = View.VISIBLE
                    }
                    R.id.articleFragment -> {
                        isHome =false
                        bottom_nav.visibility = View.INVISIBLE
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