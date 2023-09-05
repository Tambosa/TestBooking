package com.example.feature_booking.domain

import com.example.feature_booking.domain.entity.Reservation

interface ReservationRepo {
    suspend fun getReservation(): Reservation
}