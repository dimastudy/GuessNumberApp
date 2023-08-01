package com.justadev.guessnumber.di

import com.justadev.guessnumber.data.network.BaseCloudDataSource
import com.justadev.guessnumber.data.network.CloudDataSource
import com.justadev.guessnumber.data.network.RandomNumberApi
import dagger.Binds
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
interface NetworkModule {

    @Binds
    @Singleton
    fun bindCloudDataSource(cloudDataSource: BaseCloudDataSource) : CloudDataSource



}


@Module
@InstallIn(SingletonComponent::class)
object NetworkProvidesModule {

    @Provides
    @Singleton
    fun provideRandomNumberApi() : RandomNumberApi {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.randomnumberapi.com/api/v1.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(RandomNumberApi::class.java)
    }

}