package com.example.feature_hotel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.core_ui.R
import com.example.feature_hotel.databinding.FragmentHotelBinding
import com.example.feature_hotel.domain.entity.Hotel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@AndroidEntryPoint
class HotelFragment : Fragment() {

    private var _binding: FragmentHotelBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HotelViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        viewModel.getHotel()
    }

    private fun observeViewModel() {
        viewModel.data.observe(viewLifecycleOwner) { hotel ->
            renderData(hotel)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.shimmerLayout.visibility = View.VISIBLE
                binding.contentContainer.visibility = View.GONE
                binding.shimmerLayout.startShimmer()
            } else {
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
                binding.contentContainer.visibility = View.VISIBLE
            }
        }
    }

    private fun renderData(hotel: Hotel) {
        Log.d("@@@", "@renderData: ")
        val carouselList = mutableListOf<CarouselItem>()
        hotel.imageUrls.forEach {
            carouselList.add(CarouselItem(imageUrl = it))
        }
        with(binding) {
            hotelImageCarousel.setData(carouselList)
            rating.text = hotel.rating.toString()
            ratingName.text = hotel.ratingName
            hotelName.text = hotel.name
            address.text = hotel.address
            price.text = String.format(
                resources.getString(R.string.price_with_placeholder),
                hotel.minimalPrice
            )
            priceForIt.text = hotel.priceForIt
            description.text = hotel.description
        }
        binding.peculiarities.removeAllViews()
        hotel.peculiarities.forEach {
            Chip(requireContext()).apply {
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