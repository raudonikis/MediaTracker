package com.raudonikis.movietracker.di

import android.content.Context
import coil.ImageLoader
import com.google.gson.Gson
import com.raudonikis.movietracker.api.MediaApi
import com.raudonikis.movietracker.api.util.MediaApiConstants
import com.raudonikis.movietracker.api.util.MediaInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideImageLoader(@ApplicationContext context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .crossfade(true)
            .build()
    }
}