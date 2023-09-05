package com.example.feature_hotel_details.data

import com.example.core_network.BookingApi
import com.example.feature_hotel_details.domain.HotelDetailsRepo
import com.example.feature_hotel_details.domain.entity.HotelDetails
import com.example.feature_hotel_details.domain.entity.toHotelDetails

class HotelDetailsRepoImpl(private val api: BookingApi) : HotelDetailsRepo {
    override suspend fun getHotelDetails(): List<HotelDetails> =
        api.getHotelDetails().rooms.map { it.toHotelDetails() }
}