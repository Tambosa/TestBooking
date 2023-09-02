package com.example.feature_booking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_booking.domain.ReservationRepo
import com.example.feature_booking.domain.entity.Reservation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(private val repo: ReservationRepo): ViewModel() {
    private val _data = MutableLiveData<Reservation>()
    val data: LiveData<Reservation> = _data

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getHotelDetails() {
        viewModelScope.launch {
            _isLoading.value = true
            _data.value = repo.getReservation()
            delay(1000)
            _isLoading.value = false
        }
    }
}