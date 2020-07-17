package com.raudonikis.movietracker.di

import com.google.gson.Gson
import com.raudonikis.movietracker.api.MediaApi
import com.raudonikis.movietracker.api.MediaApiConstants
import com.raudonikis.movietracker.api.MediaInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMovieApiService(okHttpClient: OkHttpClient): MediaApi {
        return Retrofit.Builder()
            .baseUrl(MediaApiConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MediaApi::class.java)
    }

    @Provides
    fun provideOkHttpClient(movieInterceptor: MediaInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(movieInterceptor)
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}