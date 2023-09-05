package com.example.core_network.dto.hotel_details_dto

import com.google.gson.annotations.SerializedName

data class HotelDetailsDto(
    @SerializedName("rooms") val rooms: List<HotelDetailsDtoRoom>
)