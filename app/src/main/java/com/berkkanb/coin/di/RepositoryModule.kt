package com.berkkanb.coin.di

import com.berkkanb.coin.data.repository.CoinDataRepositoryImpl
import com.berkkanb.coin.domain.CoinDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideCoinDataRepository(repositoryImpl: CoinDataRepositoryImpl): CoinDataRepository

}