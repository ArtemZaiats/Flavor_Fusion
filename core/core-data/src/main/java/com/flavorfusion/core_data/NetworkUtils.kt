package com.flavorfusion.core_data

import android.content.Context
import android.net.ConnectivityManager
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

private const val HOST = "8.8.8.8"
private const val PORT = 53
private const val TIMEOUT = 4000

object NetworkUtils {

    fun checkNetworkState(context: Context): NetworkState {
        val networkAvailable = isNetworkAvailable(context)
        val internetAvailable = isInternetAvailable()

        return when {
            networkAvailable && internetAvailable -> NetworkState.AVAILABLE
            networkAvailable && !internetAvailable -> NetworkState.NOT_PING
            else -> NetworkState.NOT_CONNECT
        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            networkCapabilities.hasTransport(android.net.NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(android.net.NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            networkCapabilities.hasTransport(android.net.NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    private fun isInternetAvailable(): Boolean {
        return try {
            Socket().use { socket -> socket.connect(InetSocketAddress(HOST, PORT), TIMEOUT) }
            true
        } catch (e: IOException) {
            false
        }
    }
}