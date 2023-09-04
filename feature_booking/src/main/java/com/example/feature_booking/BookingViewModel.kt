package com.example.feature_booking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.feature_booking.domain.BookingDisplayableItem
import com.example.feature_booking.domain.ReservationRepo
import com.example.feature_booking.domain.entity.AddTourist
import com.example.feature_booking.domain.entity.Reservation
import com.example.feature_booking.domain.entity.Tourist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(private val repo: ReservationRepo) : ViewModel() {
    private val _data = MutableLiveData<Reservation>()
    val data: LiveData<Reservation> = _data

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _touristList = MutableLiveData(mutableListOf(Tourist(), AddTourist()))
    val touristList: LiveData<List<BookingDisplayableItem>> = _touristList.map { it.toList() }
    fun getHotelDetails() {
        viewModelScope.launch {
            _isLoading.value = true
            _data.value = repo.getReservation()
            delay(1000)
            _isLoading.value = false
        }
    }

    fun addTourist() {
        _touristList.value = _touristList.value?.apply {
            this.add(_touristList.value!!.size - 1, Tourist())
        }
    }
}