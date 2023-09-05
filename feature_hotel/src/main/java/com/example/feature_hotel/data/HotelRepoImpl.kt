package com.example.feature_hotel.data

import com.example.core_network.BookingApi
import com.example.feature_hotel.domain.HotelRepo
import com.example.feature_hotel.domain.entity.toHotel

class HotelRepoImpl(private val api: BookingApi) : HotelRepo {
    override suspend fun getHotel() = api.getHotel().toHotel()
}