package com.hamidreza.newsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response

class Connectivity(private val context: Context): Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())  throw NoInternetExceptions("connectivity")
        return chain.proceed(chain.request())
    }

    // upper than api 28 deprecated
    private fun isInternetAvailable(): Boolean{
        val manager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        manager.activeNetworkInfo.also {
            return it != null && it.isConnectedOrConnecting
        }
    }

}
