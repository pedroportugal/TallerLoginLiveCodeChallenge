package com.ballyscorp.ballylive.ui.auth.login.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

private const val USERNAME = "pedro"
private const val CREDENTIALS = "test12345"

class LoginViewModel() : ViewModel() {
    private val userNameFlow = MutableStateFlow("")
    private val passwordFlow = MutableStateFlow("")
    private val shouldShowLoginFailedMessageFlow = MutableStateFlow(false)

    private var onSuccessCallback: ((String) -> Unit)? = null

    val state = combine(
        userNameFlow,
        passwordFlow,
        shouldShowLoginFailedMessageFlow,
    ) { userName, password, shouldShowLoginFailedMessage ->

        LoginScreenState(
            userName = userName,
            password = password,
            shouldShowLoginFailedMessage = shouldShowLoginFailedMessage,
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        LoginScreenState()
    )

    fun onUserNameChanged(value: String) {
        userNameFlow.update { value }
        shouldShowLoginFailedMessageFlow.update { false }
    }

    fun onPasswordChanged(value: String) {
        passwordFlow.update { value }
        shouldShowLoginFailedMessageFlow.update { false }
    }

    fun onLogin(userName: String, password: String) {
        shouldShowLoginFailedMessageFlow.update { false }
        if (userName.isEmpty() ||
            userName != USERNAME ||
            password.isEmpty() ||
            password != CREDENTIALS
        ) {
            shouldShowLoginFailedMessageFlow.update { true }
            return
        }

        onSuccessCallback?.invoke(userName)
    }

    fun setOnSuccess(onSuccess: (String) -> Unit) {
        onSuccessCallback = onSuccess
    }
}
