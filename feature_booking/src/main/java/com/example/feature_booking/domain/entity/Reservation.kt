package com.example.feature_booking.domain.entity

import com.example.core_network.dto.reservation_dto.ReservationDto

data class Reservation(
    val arrivalCountry: String,
    val departure: String,
    val fuelCharge: Int,
    val horating: Int,
    val hotelAddress: String,
    val hotelName: String,
    val id: Int,
    val numberOfNights: Int,
    val nutrition: String,
    val ratingName: String,
    val room: String,
    val serviceCharge: Int,
    val tourDateStart: String,
    val tourDateStop: String,
    val tourPrice: Int
)

fun ReservationDto.toReservation() = Reservation(
    arrivalCountry = arrivalCountry,
    departure = departure,
    fuelCharge = fuelCharge,
    horating = horating,
    hotelAddress = hotelAddress,
    hotelName = hotelName,
    id = id,
    numberOfNights = numberOfNights,
    nutrition = nutrition,
    ratingName = ratingName,
    room = room,
    serviceCharge = serviceCharge,
    tourDateStart = tourDateStart,
    tourDateStop = tourDateStop,
    tourPrice = tourPrice,
)
