package com.example.feature_hotel_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_hotel_details.domain.HotelDetailsRepo
import com.example.feature_hotel_details.domain.entity.HotelDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelDetailsViewModel @Inject constructor(private val repo: HotelDetailsRepo) : ViewModel() {
    private val _data = MutableLiveData<List<HotelDetails>>()
    val data: LiveData<List<HotelDetails>> = _data

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getHotelDetails() {
        viewModelScope.launch {
            _isLoading.value = true
            _data.value = repo.getHotelDetails()
            delay(1000)
            _isLoading.value = false
        }
    }
}