package com.muammarahlnn.urflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.muammarahlnn.urflix.core.designsystem.theme.UrflixTheme
import com.muammarahlnn.urflix.ui.UrflixApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            UrflixTheme {
                UrflixApp()
            }
        }
    }
}