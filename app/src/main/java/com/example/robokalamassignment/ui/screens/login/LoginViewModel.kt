package com.example.robokalamassignment.ui.screens.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.robokalamassignment.data.preferences.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _isEmailError = MutableStateFlow(false)
    val isEmailError: StateFlow<Boolean> = _isEmailError.asStateFlow()

    private val _isPasswordError = MutableStateFlow(false)
    val isPasswordError: StateFlow<Boolean> = _isPasswordError.asStateFlow()

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        _isEmailError.value = false
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        _isPasswordError.value = false
    }

    fun validateAndSaveCredentials(onSuccess: () -> Unit) {
        if (!isValidEmail(_email.value)) {
            _isEmailError.value = true
            return
        }

        if (!isValidPassword(_password.value)) {
            _isPasswordError.value = true
            return
        }

        viewModelScope.launch {
            userPreferences.saveUserCredentials(_email.value, _password.value)
            onSuccess()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6 // Minimum 6 characters
    }

    class Factory(private val userPreferences: UserPreferences) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(userPreferences) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
} 