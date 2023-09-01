package com.example.feature_hotel.domain

import com.example.feature_hotel.domain.entity.Hotel

interface HotelRepo {
    suspend fun getHotel(): Hotel
}