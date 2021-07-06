package com.example.muezza.di

import com.example.muezza.data.Api
import com.example.muezza.repository.PetsRepository
import com.example.muezza.repository.Repository
import com.example.muezza.util.Constant.BASE_URL
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
object AppModule {

    // DI Function to provide Repository instance
    @Singleton
    @Provides
    fun provideRepository(
        api: Api
    ) = Repository(api)

    // DI Function to provide PetsRepository instance
    @Singleton
    @Provides
    fun providePetsRepository(
        api: Api
    ) = PetsRepository(api)


    // DI Function to provide API Instance
    @Singleton
    @Provides
    fun provideApi(): Api{
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(Api::class.java)
    }


}