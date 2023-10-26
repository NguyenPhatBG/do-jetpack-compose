package com.phatnv.do_jetpack_compose

import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phatnv.do_jetpack_compose.routes.AppNavigator
import com.phatnv.do_jetpack_compose.theme.AppTheme
import com.phatnv.do_jetpack_compose.utils.NetworkChangeReceiver

class MainActivity : ComponentActivity(), NetworkChangeReceiver.OnNetworkChangeListener {
    private lateinit var networkChangeReceiver: NetworkChangeReceiver
    private lateinit var connectivityManager: ConnectivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkChangeReceiver = NetworkChangeReceiver()
        networkChangeReceiver.onNetworkChangeListener = this

        /**
         * Trong Kotlin, "::" không phải là từ khóa, mà là toán tử phân giải phạm vi còn được gọi
         * là toán tử "double colon". Nó được sử dụng để truy cập thành viên hoặc hàm của một lớp
         * hoặc đối tượng.
         */
        connectivityManager = getSystemService(ConnectivityManager::class.java)
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colors.primary,
                    shadowElevation = 2.dp
                ) {
                    AppNavigator()
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    override fun onNetworkAvailable() {
        // Perform actions when the internet connection is available again
        Toast.makeText(this, "Internet connection is available.", Toast.LENGTH_SHORT).show()
    }

    override fun onNetworkLost() {
        // Perform actions when the internet connection is lost
        Toast.makeText(this, "No internet connection.", Toast.LENGTH_SHORT).show()
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            onNetworkAvailable()
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            onNetworkLost()
        }
    }

}
