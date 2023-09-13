package com.project.coinswap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.project.coinswap.presentation.mainscreen.MainScreen
import com.project.coinswap.presentation.theme.CoinSwapTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinSwapTheme {
                MainScreen()
            }
        }
    }
}