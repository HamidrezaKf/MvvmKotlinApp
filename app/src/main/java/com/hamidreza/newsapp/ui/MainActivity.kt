package com.hamidreza.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.hamidreza.newsapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_nav.setupWithNavController(nav_host.findNavController())
        nav_host.findNavController().addOnDestinationChangedListener { controller, destination, arguments ->

            when(destination.id){
                R.id.splashFragment -> bottom_nav.visibility = View.INVISIBLE
                R.id.introSliderFragment -> bottom_nav.visibility = View.INVISIBLE
                R.id.homeFragment -> bottom_nav.visibility = View.VISIBLE
                R.id.savedFragment -> bottom_nav.visibility = View.VISIBLE
                R.id.profileFragment -> bottom_nav.visibility = View.VISIBLE
            }
        }
    }

}