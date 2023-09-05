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
import com.example.feature_booking.domain.entity.TouristDisplayable
import com.example.feature_booking.domain.entity.checkFields
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(private val repo: ReservationRepo) : ViewModel() {
    private val _data = MutableLiveData<Reservation>()
    val data: LiveData<Reservation> = _data

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _touristList =
        MutableLiveData(mutableListOf(TouristDisplayable(Tourist()), AddTourist))
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
            this.add(_touristList.value!!.size - 1, TouristDisplayable(Tourist()))
        }
    }

    fun updateTouristList(adapterList: List<BookingDisplayableItem>): Boolean {
        val newList = mutableListOf<BookingDisplayableItem>()
        adapterList.forEach {
            if (it is TouristDisplayable) {
                newList.add(TouristDisplayable(it.tourist, it.tourist.checkFields()))
            }
        }
        newList.add(AddTourist)
        _touristList.value = newList
        newList.forEach {
            if (it is TouristDisplayable) {
                if (it.requiredFields.isNotEmpty()) return false
            }
        }
        return true
    }
}