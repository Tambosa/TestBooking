package com.example.core_network.dto.hotel_dto

import com.google.gson.annotations.SerializedName

data class HotelDtoAbout(
    @SerializedName("description") val description: String,
    @SerializedName ("peculiarities") val peculiarities: List<String>
)