package com.android.weatherapp.ui.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.lifecycleScope
import com.android.weatherapp.R
import com.android.weatherapp.ui.presentation.main.ui.theme.Purple80
import com.android.weatherapp.ui.presentation.main.ui.theme.WeatherAppTheme
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Purple80
                ) {
                    SplashImage()
                    lifecycleScope.launchWhenCreated {
                        delay(2000)
                        val intent = Intent(this@SplashActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }

}

@Composable
fun SplashImage() {
    Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "SplashImage")
}

