package com.example.feature_hotel_details.domain

import com.example.feature_hotel_details.domain.entity.HotelDetails

interface HotelDetailsRepo {
    suspend fun getHotelDetails(): List<HotelDetails>
}