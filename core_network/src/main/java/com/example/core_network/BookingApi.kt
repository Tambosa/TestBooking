package com.example.core_network

import com.example.core_network.dto.hotel_details_dto.HotelDetailsDto
import com.example.core_network.dto.hotel_dto.HotelDto
import com.example.core_network.dto.reservation_dto.ReservationDto
import retrofit2.http.GET

interface BookingApi {
    @GET("35e0d18e-2521-4f1b-a575-f0fe366f66e3")
    suspend fun getHotel(): HotelDto

    @GET("f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun getHotelDetails(): HotelDetailsDto

    @GET("e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun getReservation(): ReservationDto
}