package com.prmto.poxedexkmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.prmto.poxedexkmm.android.core.navigation.PoxedexNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                WindowCompat.setDecorFitsSystemWindows(window, true)
                PoxedexNavigation()
            }
        }
    }
}