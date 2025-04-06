package com.fbb.funapp.di

import com.fbb.funapp.data.remote.FirebaseDataSource
import com.fbb.funapp.data.repository.MatchRepositoryImpl
import com.fbb.funapp.domain.repository.MatchRepository
import com.fbb.funapp.domain.usecase.GenerateTeamsUseCase
import com.fbb.funapp.domain.usecase.SaveMatchDataUseCase
import com.fbb.funapp.domain.usecase.ScheduleMatchesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGenerateTeamsUseCase() = GenerateTeamsUseCase()

    @Provides
    fun provideScheduleMatchesUseCase() = ScheduleMatchesUseCase()

    @Provides
    fun provideFirebaseDataStore(): FirebaseDataSource = FirebaseDataSource()

    @Provides
    fun provideMatchRepository(
        firebaseDataSource: FirebaseDataSource
    ): MatchRepository = MatchRepositoryImpl(firebaseDataSource = firebaseDataSource)

    @Provides
    fun provideSaveMatchDataUseCase(
        repository: MatchRepository
    ): SaveMatchDataUseCase = SaveMatchDataUseCase(repository = repository)

}