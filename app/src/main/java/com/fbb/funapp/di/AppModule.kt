package com.fbb.funapp.di

import com.fbb.funapp.data.remote.FirebaseDataSource
import com.fbb.funapp.data.repository.MatchRepositoryImpl
import com.fbb.funapp.domain.repository.MatchRepository
import com.fbb.funapp.domain.usecase.MatchUseCases
import com.fbb.funapp.domain.usecase.matchs.GenerateMatchUseCase
import com.fbb.funapp.domain.usecase.matchs.GetHistorySessionUseCase
import com.fbb.funapp.domain.usecase.matchs.GetMatchRoundUseCase
import com.fbb.funapp.domain.usecase.matchs.GetSessionByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseDataStore(): FirebaseDataSource = FirebaseDataSource()

    @Provides
    fun provideMatchRepository(
        firebaseDataSource: FirebaseDataSource
    ): MatchRepository = MatchRepositoryImpl(firebaseDataSource = firebaseDataSource)

    @Provides
    fun provideMatchUseCases(
        repository: MatchRepository
    ): MatchUseCases = MatchUseCases(
        generateMatchUseCase = GenerateMatchUseCase(repository = repository),
        getHistorySessionUseCase = GetHistorySessionUseCase(repository = repository),
        getSessionByIdUseCase = GetSessionByIdUseCase(repository = repository),
        getMatchesUseCase = GetMatchRoundUseCase(repository = repository)
    )

}