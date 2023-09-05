package com.example.feature_booking.domain.entity

import com.example.feature_booking.domain.BookingDisplayableItem

data class Tourist(
    var name: String = "",
    var surname: String = "",
    var dateOfBirth: String = "",
    var citizenship: String = "",
    var passportNumber: String = "",
    var passportExpireDate: String = "",
)

data class TouristDisplayable(
    val tourist: Tourist,
    var requiredFields: List<TouristRequiredFields> = listOf()
) : BookingDisplayableItem

enum class TouristRequiredFields {
    NAME,
    SURNAME,
    DATE_OF_BIRTH,
    CITIZENSHIP,
    PASSPORT_NUMBER,
    PASSPORT_EXPIRE_DATE,
}

fun Tourist.checkFields(): List<TouristRequiredFields> {
    val requiredFields = mutableListOf<TouristRequiredFields>()
    if (name.isBlank()) requiredFields.add(TouristRequiredFields.NAME)
    if (surname.isBlank()) requiredFields.add(TouristRequiredFields.SURNAME)
    if (dateOfBirth.isBlank()) requiredFields.add(TouristRequiredFields.DATE_OF_BIRTH)
    if (citizenship.isBlank()) requiredFields.add(TouristRequiredFields.CITIZENSHIP)
    if (passportNumber.isBlank()) requiredFields.add(TouristRequiredFields.PASSPORT_NUMBER)
    if (passportExpireDate.isBlank()) requiredFields.add(TouristRequiredFields.PASSPORT_EXPIRE_DATE)
    return requiredFields.toList()
}