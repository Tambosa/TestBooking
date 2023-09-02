package com.example.feature_hotel_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.R
import com.example.feature_hotel_details.databinding.FragmentHotelDetailsBinding
import com.example.feature_hotel_details.domain.entity.HotelDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelDetailsFragment : Fragment() {
    private var _binding: FragmentHotelDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: HotelDetailsViewModel by viewModels()

    private val hotelDetailsAdapter =
        HotelDetailsAdapter(onChooseRoomBtnClick = { position -> navigateDown(position) })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActionBar()
        observeViewModel()
        initRecycler()
        viewmodel.getHotelDetails()
    }

    private fun initRecycler() {
        binding.recyclerRooms.apply {
            this.adapter = hotelDetailsAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
        }
    }

    private fun observeViewModel() {
        viewmodel.data.observe(viewLifecycleOwner) { roomList ->
            renderRecyclerData(roomList)
        }
        viewmodel.isLoading.observe(viewLifecycleOwner) { isLoading ->

        }
    }

    private fun renderRecyclerData(roomList: List<HotelDetails>) {
        val oldList = hotelDetailsAdapter.items ?: listOf()
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = oldList.size

            override fun getNewListSize() = roomList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                oldList[oldItemPosition]::class == roomList[newItemPosition]::class && oldList[oldItemPosition] == roomList[newItemPosition]

            override fun areContentsTheSame(
                oldItemPosition: Int, newItemPosition: Int
            ) = oldList[oldItemPosition] == roomList[newItemPosition]
        })
        hotelDetailsAdapter.items = roomList
        diff.dispatchUpdatesTo(hotelDetailsAdapter)
    }

    private fun initActionBar() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = arguments?.getString("title")
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
        }
    }

    private fun navigateDown(position: Int) {
        val request = NavDeepLinkRequest.Builder
            .fromUri(resources.getString(R.string.deeplink_booking).toUri())
            .build()
        findNavController().navigate(request)
    }
}