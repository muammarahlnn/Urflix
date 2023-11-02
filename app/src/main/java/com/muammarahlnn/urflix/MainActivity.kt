package com.muammarahlnn.urflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.muammarahlnn.urflix.core.designsystem.theme.UrflixTheme
import com.muammarahlnn.urflix.ui.UrflixApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UrflixTheme {
                UrflixApp()
            }
        }
    }
}