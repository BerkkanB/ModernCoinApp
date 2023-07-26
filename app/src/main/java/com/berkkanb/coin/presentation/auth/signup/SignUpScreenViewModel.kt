package com.berkkanb.coin.presentation.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkkanb.coin.domain.BaseAuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val baseAuthRepository: BaseAuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpScreenUIState())
    val uiState = _uiState.asStateFlow()

    fun setEmail(value: String) {
        _uiState.update {
            it.copy(email = value)
        }
    }

    fun setPassword(value: String) {
        _uiState.update {
            it.copy(password = value)
        }
    }

    fun signUpUser() {
        viewModelScope.launch {
            setLoadingStatus(true)
            try {
                val user = baseAuthRepository.signUpWithEmailPassword(
                    uiState.value.email,
                    uiState.value.password
                )
                user?.let { firebaseUser ->
                    _uiState.update {
                        it.copy(firebaseUser = firebaseUser)
                    }
                    setLoadingStatus(false)
                } ?: kotlin.run {
                    setLoadingStatus(false)
                    setHasError()
                }
            } catch (e: Exception) {
                setHasError()
                setLoadingStatus(false)
            }
        }
    }

    private fun setLoadingStatus(status: Boolean) {
        _uiState.update {
            it.copy(
                isLoading = status
            )
        }
    }

    private fun setHasError() {
        _uiState.update {
            it.copy(
                hasError = true
            )
        }
    }


}

data class SignUpScreenUIState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val firebaseUser: FirebaseUser? = null
)