package com.example.feature_booking.data

import com.example.core_network.BookingApi
import com.example.feature_booking.domain.ReservationRepo
import com.example.feature_booking.domain.entity.toReservation

class ReservationRepoImpl(private val api: BookingApi) : ReservationRepo {
    override suspend fun getReservation() = api.getReservation().toReservation()
}