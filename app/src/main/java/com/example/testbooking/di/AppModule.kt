package com.example.testbooking.di

import com.example.core_network.BookingApi
import com.example.core_network.Constants
import com.example.feature_hotel.data.HotelRepoImpl
import com.example.feature_hotel.domain.HotelRepo
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
    @Provides
    @Singleton
    fun provideBookingApi(): BookingApi {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.MOCKY_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookingApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHotelRepo(): HotelRepo {
        return HotelRepoImpl(provideBookingApi())
    }
}