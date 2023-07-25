package com.berkkanb.coin

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
class MainViewModel @Inject constructor(
    private val baseAuthRepository: BaseAuthRepository
) : ViewModel() {

    private val _authState = MutableStateFlow(FirebaseAuthState())
    val authState = _authState.asStateFlow()

    init {
        getCurrentUser()
    }


    private fun getCurrentUser(){
        viewModelScope.launch {
            setLoadingStatus(true)
            baseAuthRepository.getCurrentUser()?.let { firebaseUser ->
                _authState.update {
                    it.copy(currentUser = firebaseUser)
                }
            }
            setLoadingStatus(false)
        }
    }

    private fun setLoadingStatus(status: Boolean) {
        _authState.update {
            it.copy(
                isLoading = status
            )
        }
    }

    private fun setHasError() {
        _authState.update {
            it.copy(
                hasError = true
            )
        }
    }
}

data class FirebaseAuthState(
    val currentUser: FirebaseUser? = null,
    val isLoading:Boolean = false,
    val hasError:Boolean = false
)