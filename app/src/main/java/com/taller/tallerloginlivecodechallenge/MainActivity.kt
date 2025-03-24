package com.taller.tallerloginlivecodechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.activity.enableEdgeToEdge
import com.ballyscorp.ballylive.ui.auth.login.login.LoginScreen
import com.ballyscorp.ballylive.ui.auth.login.login.MainViewModel
import com.taller.tallerloginlivecodechallenge.login.Greeting
import com.taller.tallerloginlivecodechallenge.ui.theme.TallerLoginLiveCodeChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = MainViewModel()
            val state by viewModel.state.collectAsState()

            TallerLoginLiveCodeChallengeTheme {
                if (state.shouldGoToGreeting) {
                    Greeting(state.userName)
                } else {
                    LoginScreen(
                        onSuccess = viewModel::navigateToGreeting
                    )
                }
            }
        }
    }
}