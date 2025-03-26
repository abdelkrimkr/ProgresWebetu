package com.zako.webetu.auth.login.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zako.webetu.auth.login.model.RemoteDataSource
import com.zako.webetu.utils.asString
import com.zako.webetu.utils.shortIoToast
import com.zako.webetu.utils.shortToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class LoginScreenViewModel(
    private val remote: RemoteDataSource
) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    fun action(action: LoginScreenActions) {
        when (action) {
            is LoginScreenActions.RegistrationNumberChange -> {
                _state.value = _state.value.copy(registrationNumber = action.registrationNumber)
            }

            is LoginScreenActions.PasswordChange -> {
                _state.value = _state.value.copy(password = action.password)
            }

            is LoginScreenActions.ShowPasswordClicked -> {
                _state.value = _state.value.copy(isPasswordVisible = true)
            }

            is LoginScreenActions.HidePasswordClicked -> {
                _state.value = _state.value.copy(isPasswordVisible = false)
            }

            is LoginScreenActions.LoginClicked -> {
                login(
                    _state.value.registrationNumber,
                    _state.value.password
                )
            }
        }
    }

    fun login(
        registrationNumber: String,
        password: String
    ) {
        val context: Context = get()

        if (registrationNumber.isBlank() or password.isBlank()) {
            context.shortToast("please fill all the fields !")
        }
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            val result = remote.login(
                registrationNumber = registrationNumber,
                password = password
            )

            result
                .onRight { loginResponse ->
                    context.shortIoToast("login successfully")
                    context.shortIoToast("userID : ${loginResponse.userId}")
                }
                .onLeft { appError ->
                    context.shortIoToast("login fail")
                    appError.errorMessage?.asString(context)?.let {
                        context.shortIoToast(it)
                    }
                }

            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }
}
