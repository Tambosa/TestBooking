package com.example.core_network.dto.hotel_details_dto

import com.google.gson.annotations.SerializedName

data class HotelDetailsDtoRoom(
    @SerializedName("id") val id: Int,
    @SerializedName("image_urls") val imageUrls: List<String>,
    @SerializedName("name") val name: String,
    @SerializedName("peculiarities") val peculiarities: List<String>,
    @SerializedName("price") val price: Int,
    @SerializedName("price_per") val pricePer: String
)