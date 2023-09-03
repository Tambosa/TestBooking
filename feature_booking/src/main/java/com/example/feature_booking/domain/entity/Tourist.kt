package com.example.feature_booking.domain.entity

import com.example.feature_booking.domain.BookingDisplayableItem

data class Tourist(
    val name: String = "",
    val surname: String = "",
    val dateOfBirth: String = "",
    val citizenship: String = "",
    val passportNumber: String = "",
    val passportExpireDate: String = "",
) : BookingDisplayableItem
