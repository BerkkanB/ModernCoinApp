package com.berkkanb.coin.data.auth

import com.google.firebase.auth.FirebaseUser

interface BaseAuthenticator {


    suspend fun signUpWithEmailPassword(email: String, password: String): FirebaseUser?

    suspend fun signInWithEmailPassword(email: String, password: String): FirebaseUser?

    fun signOut(): FirebaseUser?

    fun getUser(): FirebaseUser?

    suspend fun sendPasswordReset(email: String)
}