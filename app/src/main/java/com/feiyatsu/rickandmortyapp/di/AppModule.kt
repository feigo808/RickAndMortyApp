package com.feiyatsu.rickandmortyapp.di

import com.feiyatsu.rickandmortyapp.BuildConfig
import com.feiyatsu.rickandmortyapp.network.api.CharactersApi
import com.feiyatsu.rickandmortyapp.network.repository.CharactersRepositoryContract
import com.feiyatsu.rickandmortyapp.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val API_URL = BuildConfig.API_URL

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providesCharacterApi(retrofit: Retrofit): CharactersApi =
        retrofit.create(CharactersApi::class.java)

    @Singleton
    @Provides
    fun providesCharactersRepository(charactersApi: CharactersApi): CharactersRepositoryContract =
        CharactersRepository(charactersApi)
}