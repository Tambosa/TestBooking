package com.example.feature_hotel_details.domain.entity

import com.example.core_network.dto.hotel_details_dto.HotelDetailsDtoRoom
import com.example.feature_hotel_details.domain.HotelDetailsDisplayableItem

data class HotelDetails(
    val id: Int,
    val imageUrls: List<String>,
    val name: String,
    val peculiarities: List<String>,
    val price: Int,
    val pricePer: String
) : HotelDetailsDisplayableItem

fun HotelDetailsDtoRoom.toHotelDetails() = HotelDetails(
    id = this.id,
    imageUrls = this.imageUrls,
    name = this.name,
    peculiarities = this.peculiarities,
    price = this.price,
    pricePer = this.pricePer
)
