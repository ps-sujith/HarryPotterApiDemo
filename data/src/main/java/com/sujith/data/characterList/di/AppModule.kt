package com.sujith.data.characterList.di

import com.sujith.data.Constants.BASE_URL
import com.sujith.data.characterList.api.HarryPotterApiService
import com.sujith.data.characterList.dataSource.RemoteCharacterListDataSource
import com.sujith.data.characterList.dataSource.RemoteCharacterListDataSourceImpl
import com.sujith.data.characterList.repository.CharacterListRepositoryImpl
import com.sujith.domain.characterList.repository.CharacterListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideCharacterListRepository(remoteCharacterListDataSource: RemoteCharacterListDataSource): CharacterListRepository =
        CharacterListRepositoryImpl(remoteCharacterListDataSource)

    @Provides
    @Singleton
    fun provideRemoteCharacterListDataSource(apiService: HarryPotterApiService): RemoteCharacterListDataSource =
        RemoteCharacterListDataSourceImpl(apiService)

    @Provides
    @Singleton
    fun provideHarryPotterApiService(retrofit: Retrofit): HarryPotterApiService =
        retrofit.create(HarryPotterApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create()).build()
}