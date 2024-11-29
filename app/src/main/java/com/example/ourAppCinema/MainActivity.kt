package com.example.ourAppCinema

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ourAppCinema.presentation.Navigation.WelcomePage
import com.example.ourAppCinema.ui.theme.OurAppCinema

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OurAppCinema  {
                WelcomePage()
            }
        }
    }
}

