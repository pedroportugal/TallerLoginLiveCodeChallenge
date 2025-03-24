package com.ballyscorp.ballylive.ui.auth.login.login


data class LoginScreenState(
    val userName: String = "",
    val password: String = "",
    val shouldShowLoginFailedMessage: Boolean = false
)