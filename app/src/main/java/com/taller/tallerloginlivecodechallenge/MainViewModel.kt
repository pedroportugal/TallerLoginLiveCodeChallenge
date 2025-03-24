package com.ballyscorp.ballylive.ui.auth.login.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MainViewModel() : ViewModel() {

    private val userNameFlow = MutableStateFlow("")
    private val shouldGoToGreetingFlow = MutableStateFlow(false)

    val state = userNameFlow.combine(
        shouldGoToGreetingFlow,
    ) { userName, shouldGoToGreeting ->

        MainScreenState(
            userName = userName,
            shouldGoToGreeting = shouldGoToGreeting,
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        MainScreenState()
    )

    fun navigateToGreeting(userName: String) {
        userNameFlow.update { userName }
        shouldGoToGreetingFlow.update { true }
    }
}
