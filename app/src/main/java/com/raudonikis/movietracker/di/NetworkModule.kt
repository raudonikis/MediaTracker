package com.raudonikis.movietracker.di

import com.raudonikis.movietracker.api.MoveInterceptor
import com.raudonikis.movietracker.api.MovieApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    private const val MOVIE_API_BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    fun provideMovieApiService(okHttpClient: OkHttpClient): MovieApiService {
        return Retrofit.Builder()
            .baseUrl(MOVIE_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }

    @Provides
    fun provideOkHttpClient(movieInterceptor: MoveInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(movieInterceptor)
            .build()
    }
}