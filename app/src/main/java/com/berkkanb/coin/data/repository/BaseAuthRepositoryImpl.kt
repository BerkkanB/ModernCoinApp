package com.berkkanb.coin.data.repository

import com.berkkanb.coin.data.auth.BaseAuthenticator
import com.berkkanb.coin.domain.BaseAuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class BaseAuthRepositoryImpl @Inject constructor(
    private val authenticator: BaseAuthenticator
) : BaseAuthRepository {
    override suspend fun signInWithEmailPassword(email: String, password: String): FirebaseUser? {
        return authenticator.signInWithEmailPassword(email, password)
    }

    override suspend fun signUpWithEmailPassword(email: String, password: String): FirebaseUser? {
        return authenticator.signUpWithEmailPassword(email, password)
    }

    override fun signOut(): FirebaseUser? {
        return authenticator.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? {
        return authenticator.getUser()
    }

    override suspend fun sendResetPassword(email: String): Boolean {
        authenticator.sendPasswordReset(email)
        return true
    }
}
