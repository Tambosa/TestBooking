package com.example.feature_booking

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.R
import com.example.feature_booking.databinding.FragmentBookingBinding
import com.example.feature_booking.domain.BookingDisplayableItem
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher


@AndroidEntryPoint
class BookingFragment : Fragment() {
    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: BookingViewModel by viewModels()
    private val bookingAdapter = BookingAdapter(onAdd = { viewmodel.addTourist() })


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingBinding.inflate(inflater, container, false)
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
        binding.btnPay.setOnClickListener {
            if (binding.phoneInput.text.isNullOrBlank()) {
                binding.buyerPhoneNumberLayout.boxStrokeColor =
                    resources.getColor(R.color.error_color, null)
                binding.phoneInput.requestFocus()
            } else if (binding.emailInput.text.isNullOrBlank()) {
                binding.buyerEmailLayout.boxStrokeColor =
                    resources.getColor(R.color.error_color, null)
                binding.emailInput.requestFocus()
            } else {
                navigateDown()
            }
        }
    }

    private fun navigateDown() {
        val request = NavDeepLinkRequest.Builder
            .fromUri(resources.getString(R.string.deeplink_payment_received).toUri())
            .build()
        findNavController().navigate(request)
    }

    private fun initRecycler() {
        binding.touristRecyclerView.apply {
            this.adapter = bookingAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
        }
    }

    private fun observeViewModel() {
        viewmodel.data.observe(viewLifecycleOwner) { reservation ->
            binding.hotelName.text = reservation.hotelName
            binding.address.text = reservation.hotelAddress
            binding.rating.text = reservation.horating.toString()
            binding.ratingName.text = reservation.ratingName

            binding.flightFromApi.text = reservation.departure
            binding.countryCityApi.text = reservation.arrivalCountry
            binding.datesApi.text = reservation.tourDateStart + " - " + reservation.tourDateStop
            binding.numberOfNightsApi.text = reservation.numberOfNights.toString()
            binding.hotelApi.text = reservation.hotelName
            binding.roomApi.text = reservation.room
            binding.mealsApi.text = reservation.nutrition

            val formatWatcher = MaskFormatWatcher(
                MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
            )
            formatWatcher.installOn(binding.phoneInput)

            binding.phoneInput.addTextChangedListener { text ->
                if (text != null) {
                    if (!text.isPhoneNumber()) binding.phoneInput.error =
                        getString(R.string.input_error)
                    else {
                        binding.buyerPhoneNumberLayout.boxStrokeColor = Color.GREEN
                        binding.phoneInput.error = null
                    }
                }
            }

            binding.emailInput.addTextChangedListener { text ->
                if (text != null) {
                    if (!text.isValidEmail()) binding.emailInput.error =
                        getString(R.string.input_error)
                    else {
                        binding.buyerEmailLayout.boxStrokeColor = Color.GREEN
                        binding.emailInput.error = null
                    }
                }
            }

            binding.tourPrice.text =
                String.format(getString(R.string.price_with_ruble), reservation.tourPrice)
            binding.fuelPrice.text =
                String.format(getString(R.string.price_with_ruble), reservation.fuelCharge)
            binding.servicePrice.text =
                String.format(getString(R.string.price_with_ruble), reservation.serviceCharge)
            val total = reservation.tourPrice + reservation.fuelCharge + reservation.serviceCharge
            binding.totalPrice.text =
                String.format(getString(R.string.price_with_ruble), total)
        }

        viewmodel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.starRateContainer.visibility = View.GONE
                binding.shimmerLayout.visibility = View.VISIBLE
                binding.shimmerLayout.startShimmer()
            } else {
                binding.starRateContainer.visibility = View.VISIBLE
                binding.shimmerLayout.visibility = View.GONE
                binding.shimmerLayout.stopShimmer()
            }
        }

        viewmodel.touristList.observe(viewLifecycleOwner) { tourists ->
            renderRecyclerData(tourists)
        }
    }

    private fun renderRecyclerData(tourists: List<BookingDisplayableItem>) {
        val oldList = bookingAdapter.items ?: listOf()
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = oldList.size

            override fun getNewListSize() = tourists.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                oldList[oldItemPosition]::class == tourists[newItemPosition]::class && oldList[oldItemPosition] == tourists[newItemPosition]

            override fun areContentsTheSame(
                oldItemPosition: Int, newItemPosition: Int
            ) = oldList[oldItemPosition] == tourists[newItemPosition]
        })
        bookingAdapter.items = tourists
        diff.dispatchUpdatesTo(bookingAdapter)
    }

    private fun initActionBar() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
        }
    }
}