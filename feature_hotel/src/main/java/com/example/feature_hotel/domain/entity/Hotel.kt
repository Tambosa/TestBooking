package com.example.feature_hotel.domain.entity

import com.example.core_network.dto.hotel_dto.HotelDto

data class Hotel(
    val description: String,
    val peculiarities: List<String>,
    val address: String,
    val id: Int,
    val imageUrls: List<String>,
    val minimalPrice: Int,
    val name: String,
    val priceForIt: String,
    val rating: Int,
    val ratingName: String
)

fun HotelDto.toHotel() = Hotel(
    description = this.about.description,
    peculiarities = this.about.peculiarities,
    address = this.address,
    id = this.id,
    imageUrls = this.imageUrls,
    minimalPrice = this.minimalPrice,
    name = this.name,
    priceForIt = this.priceForIt,
    rating = this.rating,
    ratingName = this.ratingName,
)