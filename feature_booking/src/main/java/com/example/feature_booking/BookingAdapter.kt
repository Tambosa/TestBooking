package com.example.feature_booking

import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.core_ui.R
import com.example.feature_booking.databinding.ItemAddTouristBinding
import com.example.feature_booking.databinding.ItemTouristBinding
import com.example.feature_booking.domain.BookingDisplayableItem
import com.example.feature_booking.domain.entity.AddTourist
import com.example.feature_booking.domain.entity.Tourist
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class BookingAdapter(onAdd: () -> Unit) :
    ListDelegationAdapter<List<BookingDisplayableItem>>(
        touristAdapterDelegate(),
        addTouristAdapterDelegate(onAdd)
    )

fun addTouristAdapterDelegate(onAdd: () -> Unit) =
    adapterDelegateViewBinding<AddTourist, BookingDisplayableItem, ItemAddTouristBinding>({ layoutInflater, root ->
        ItemAddTouristBinding.inflate(layoutInflater, root, false)
    }) {
        bind {
            binding.btnAdd.setOnClickListener { onAdd() }
        }
    }


fun touristAdapterDelegate() =
    adapterDelegateViewBinding<Tourist, BookingDisplayableItem, ItemTouristBinding>({ layoutInflater, root ->
        ItemTouristBinding.inflate(layoutInflater, root, false)
    }) {
        bind {
            when (layoutPosition) {
                0 -> binding.touristNumber.text = "Первый турист"
                1 -> binding.touristNumber.text = "Второй турист"
                2 -> binding.touristNumber.text = "Третий турист"
                else -> binding.touristNumber.text = "Еще один турист"
            }

            with(binding.touristNameInput) {
                addTextChangedListener {
                    item.name = it.toString()
                    if (it.isNullOrBlank()) {
                        this.error = getString(R.string.input_error)
                    } else this.error = null
                }
            }

            with(binding.touristSurnameInput) {
                item.surname = it.toString()
                addTextChangedListener {
                    if (it.isNullOrBlank()) {
                        this.error = getString(R.string.input_error)
                    } else this.error = null
                }
            }

            with(binding.touristDateOfBirthInput) {
                item.dateOfBirth = it.toString()
                addTextChangedListener {
                    if (it.isNullOrBlank()) {
                        this.error = getString(R.string.input_error)
                    } else this.error = null
                }
            }

            with(binding.touristCitizenshipInput) {
                item.citizenship = it.toString()
                addTextChangedListener {
                    if (it.isNullOrBlank()) {
                        this.error = getString(R.string.input_error)
                    } else this.error = null
                }
            }

            with(binding.touristPassportNumberInput) {
                item.passportNumber = it.toString()
                addTextChangedListener {
                    if (it.isNullOrBlank()) {
                        this.error = getString(R.string.input_error)
                    } else this.error = null
                }
            }

            with(binding.touristPassportExpireDateInput) {
                item.passportExpireDate = it.toString()
                addTextChangedListener {
                    if (it.isNullOrBlank()) {
                        this.error = getString(R.string.input_error)
                    } else this.error = null
                }
            }

            binding.btnExpand.setOnClickListener {
                if (binding.expandablePart.visibility == View.GONE) {
                    TransitionManager.beginDelayedTransition(
                        binding.touristCardView,
                        AutoTransition()
                    )
                    binding.expandablePart.visibility = View.VISIBLE
                    binding.btnExpand.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)
                } else {
                    TransitionManager.beginDelayedTransition(
                        binding.touristCardView,
                        AutoTransition()
                    )
                    binding.expandablePart.visibility = View.GONE
                    binding.btnExpand.setImageResource(R.drawable.baseline_keyboard_arrow_down_24)
                }
            }
        }
    }