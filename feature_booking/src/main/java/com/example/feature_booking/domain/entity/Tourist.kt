package com.example.feature_booking.domain.entity

import com.example.feature_booking.domain.BookingDisplayableItem

data class Tourist(
    var name: String = "",
    var surname: String = "",
    var dateOfBirth: String = "",
    var citizenship: String = "",
    var passportNumber: String = "",
    var passportExpireDate: String = "",
) : BookingDisplayableItem

fun Tourist.isFilled() = (
        name.isNotBlank()
                && surname.isNotBlank()
                && dateOfBirth.isNotBlank()
                && citizenship.isNotBlank()
                && passportNumber.isNotBlank()
                && passportExpireDate.isNotBlank()
        )
