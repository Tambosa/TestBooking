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
import com.example.feature_booking.domain.entity.TouristDisplayable
import com.example.feature_booking.domain.entity.TouristRequiredFields
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
    adapterDelegateViewBinding<TouristDisplayable, BookingDisplayableItem, ItemTouristBinding>({ layoutInflater, root ->
        ItemTouristBinding.inflate(layoutInflater, root, false)
    }) {
        bind {
            with(binding) {
                touristNameInput.setText(item.tourist.name)
                touristSurnameInput.setText(item.tourist.surname)
                touristDateOfBirthInput.setText(item.tourist.dateOfBirth)
                touristCitizenshipInput.setText(item.tourist.citizenship)
                touristPassportNumberInput.setText(item.tourist.passportNumber)
                touristPassportExpireDateInput.setText(item.tourist.passportExpireDate)
            }

            when (layoutPosition) {
                0 -> binding.touristNumber.text = "Первый турист"
                1 -> binding.touristNumber.text = "Второй турист"
                2 -> binding.touristNumber.text = "Третий турист"
                else -> binding.touristNumber.text = "Еще один турист"
            }
            item.requiredFields.forEach { emptyField ->
                when (emptyField) {
                    TouristRequiredFields.NAME -> {
                        binding.touristName.boxBackgroundColor =
                            getColor(R.color.error_color)
                    }

                    TouristRequiredFields.SURNAME -> {
                        binding.touristSurname.boxBackgroundColor =
                            getColor(R.color.error_color)
                    }

                    TouristRequiredFields.CITIZENSHIP -> {
                        binding.touristCitizenship.boxBackgroundColor =
                            getColor(R.color.error_color)
                    }

                    TouristRequiredFields.DATE_OF_BIRTH -> {
                        binding.touristDateOfBirth.boxBackgroundColor =
                            getColor(R.color.error_color)
                    }

                    TouristRequiredFields.PASSPORT_EXPIRE_DATE -> {
                        binding.touristPassportExpireDate.boxBackgroundColor =
                            getColor(R.color.error_color)
                    }

                    TouristRequiredFields.PASSPORT_NUMBER -> {
                        binding.touristPassportNumber.boxBackgroundColor =
                            getColor(R.color.error_color)
                    }
                }
            }

            with(binding.touristNameInput) {
                addTextChangedListener {
                    item.tourist.name = it.toString()
                    if (!it.isNullOrBlank()) {
                        binding.touristName.boxBackgroundColor =
                            getColor(R.color.background_container)
                    } else {
                        binding.touristName.boxBackgroundColor =
                            getColor(R.color.error_color)
                    }
                }
            }

            with(binding.touristSurnameInput) {
                addTextChangedListener {
                    item.tourist.surname = it.toString()
                    if (!it.isNullOrBlank()) {
                        binding.touristSurname.boxBackgroundColor =
                            getColor(R.color.background_container)
                    } else {
                        binding.touristSurname.boxBackgroundColor =
                            getColor(R.color.error_color)
                    }
                }
            }

            with(binding.touristDateOfBirthInput) {
                addTextChangedListener {
                    item.tourist.dateOfBirth = it.toString()
                    if (!it.isNullOrBlank()) {
                        binding.touristDateOfBirth.boxBackgroundColor =
                            getColor(R.color.background_container)
                    } else {
                        binding.touristDateOfBirth.boxBackgroundColor =
                            getColor(R.color.error_color)
                    }
                }
            }

            with(binding.touristCitizenshipInput) {
                addTextChangedListener {
                    item.tourist.citizenship = it.toString()
                    if (!it.isNullOrBlank()) {
                        binding.touristCitizenship.boxBackgroundColor =
                            getColor(R.color.background_container)
                    } else {
                        binding.touristCitizenship.boxBackgroundColor =
                            getColor(R.color.error_color)
                    }
                }
            }

            with(binding.touristPassportNumberInput) {
                addTextChangedListener {
                    item.tourist.passportNumber = it.toString()
                    if (!it.isNullOrBlank()) {
                        binding.touristPassportNumber.boxBackgroundColor =
                            getColor(R.color.background_container)
                    } else {
                        binding.touristPassportNumber.boxBackgroundColor =
                            getColor(R.color.error_color)
                    }
                }
            }

            with(binding.touristPassportExpireDateInput) {
                addTextChangedListener {
                    item.tourist.passportExpireDate = it.toString()
                    if (!it.isNullOrBlank()) {
                        binding.touristPassportExpireDate.boxBackgroundColor =
                            getColor(R.color.background_container)
                    } else {
                        binding.touristPassportExpireDate.boxBackgroundColor =
                            getColor(R.color.error_color)
                    }
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