package com.example.feature_hotel_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.feature_hotel_details.databinding.FragmentHotelDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelDetailsFragment : Fragment() {
    private var _binding: FragmentHotelDetailsBinding? = null
    private val binding get() = _binding!!

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
    }

    private fun initActionBar() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = arguments?.getString("title")
            setDisplayHomeAsUpEnabled(true)
        }
    }
}