package com.example.feature_hotel_details

import com.example.core_ui.R
import com.example.feature_hotel_details.databinding.ItemRoomBinding
import com.example.feature_hotel_details.domain.HotelDetailsDisplayableItem
import com.example.feature_hotel_details.domain.entity.HotelDetails
import com.google.android.material.chip.Chip
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HotelDetailsAdapter(onChooseRoomBtnClick: (position: Int) -> Unit) :
    ListDelegationAdapter<List<HotelDetailsDisplayableItem>>(
        roomAdapterDelegate(onChooseRoomBtnClick),
    )

fun roomAdapterDelegate(onItemClick: (position: Int) -> Unit) =
    adapterDelegateViewBinding<HotelDetails, HotelDetailsDisplayableItem, ItemRoomBinding>({ layoutInflater, root ->
        ItemRoomBinding.inflate(layoutInflater, root, false)
    }) {
        bind {
            val carouselList = mutableListOf<CarouselItem>()
            item.imageUrls.forEach {
                carouselList.add(CarouselItem(imageUrl = it))
            }
            binding.roomImageCarousel.setData(carouselList)
            binding.btnChooseRoom.setOnClickListener { onItemClick(layoutPosition) }
            binding.roomTitle.text = item.name
            binding.price.text =
                String.format(
                    getString(R.string.price_with_ruble),
                    item.price
                )
            binding.pricePer.text = item.pricePer
            binding.peculiarities.removeAllViews()
            item.peculiarities.forEach {
                Chip(context).apply {
                    setChipBackgroundColorResource(R.color.background_container)
                    isCheckable = false
                    text = it
                    isClickable = false
                    chipStrokeWidth = 0f
                    setTextColor(resources.getColor(R.color.text_light, null))
                }.also {
                    binding.peculiarities.addView(it)
                }
            }
        }
    }