package com.example.fakeapiretrofit.di

import com.example.fakeapiretrofit.network.FakeApiQueries
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FakeApiProvider {

    @Singleton
    @Provides
    fun getOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder().addInterceptor(httpLoggingInterceptor).build()
    }

    @Singleton
    @Provides
    fun getFakeApi(okHttpClient: OkHttpClient): FakeApiQueries {
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(FakeApiQueries::class.java)
    }
}