package com.hamidreza.newsapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hamidreza.newsapp.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    val handler = Handler()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        val isLogin = sharedPreferences.getBoolean("IS_LOG_IN",false)
        handler.postDelayed(object : Runnable{
            override fun run() {
                if (isLogin)
                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                else
                    findNavController().navigate(R.id.action_splashFragment_to_introSliderFragment)
            }
        },2500)
    }

    override fun onPause() {

        super.onPause()
        handler.removeCallbacksAndMessages(null)
    }

}