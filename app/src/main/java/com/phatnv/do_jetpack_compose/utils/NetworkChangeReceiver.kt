package com.phatnv.do_jetpack_compose.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkChangeReceiver : BroadcastReceiver() {
    var onNetworkChangeListener: OnNetworkChangeListener? = null

    interface OnNetworkChangeListener {
        fun onNetworkAvailable()
        fun onNetworkLost()
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val network = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

            if (networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true) {
                onNetworkChangeListener?.onNetworkAvailable()
            } else {
                onNetworkChangeListener?.onNetworkLost()
            }
        }
    }
}