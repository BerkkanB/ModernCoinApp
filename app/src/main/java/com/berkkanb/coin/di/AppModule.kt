package com.berkkanb.coin.di

import com.berkkanb.coin.data.auth.BaseAuthenticator
import com.berkkanb.coin.data.auth.FirebaseAuthenticator
import com.berkkanb.coin.data.repository.BaseAuthRepositoryImpl
import com.berkkanb.coin.domain.BaseAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAuthenticator(): BaseAuthenticator {
        return FirebaseAuthenticator()
    }

    //TODO REMOVE REPO MODULE
    @Singleton
    @Provides
    fun provideRepository(authenticator: BaseAuthenticator): BaseAuthRepository {
        return BaseAuthRepositoryImpl(authenticator)
    }
}