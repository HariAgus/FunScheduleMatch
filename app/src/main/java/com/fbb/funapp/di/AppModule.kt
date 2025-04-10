package com.fbb.funapp.di

import com.fbb.funapp.data.remote.FirebaseDataSource
import com.fbb.funapp.data.repository.MatchRepositoryImpl
import com.fbb.funapp.domain.repository.MatchRepository
import com.fbb.funapp.domain.usecase.MatchUseCases
import com.fbb.funapp.domain.usecase.match.GenerateMatchUseCase
import com.fbb.funapp.domain.usecase.match.GenerateTeamsUseCase
import com.fbb.funapp.domain.usecase.match.GetHistorySessionUseCase
import com.fbb.funapp.domain.usecase.match.GetMatchesUseCase
import com.fbb.funapp.domain.usecase.match.GetSessionByIdUseCase
import com.fbb.funapp.domain.usecase.match.GetTeamsUseCase
import com.fbb.funapp.domain.usecase.match.SaveMatchDataUseCase
import com.fbb.funapp.domain.usecase.match.ScheduleMatchesUseCase
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
        generateTeamsUseCase = GenerateTeamsUseCase(),
        saveMatchDataUseCase = SaveMatchDataUseCase(repository = repository),
        scheduleMatchesUseCase = ScheduleMatchesUseCase(),
        getHistorySessionUseCase = GetHistorySessionUseCase(repository = repository),
        getSessionByIdUseCase = GetSessionByIdUseCase(repository = repository),
        getTeamsUseCase = GetTeamsUseCase(repository = repository),
        getMatchesUseCase = GetMatchesUseCase(repository = repository)
    )

}