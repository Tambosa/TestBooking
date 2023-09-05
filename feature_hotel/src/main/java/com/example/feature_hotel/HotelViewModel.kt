package com.example.feature_hotel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_hotel.domain.HotelRepo
import com.example.feature_hotel.domain.entity.Hotel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(private val repo: HotelRepo) : ViewModel() {
    private val _data = MutableLiveData<Hotel>()
    val data: LiveData<Hotel> = _data

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getHotel() {
        viewModelScope.launch {
            _isLoading.value = true
            _data.value = repo.getHotel()
            _isLoading.value = false
        }
    }
}