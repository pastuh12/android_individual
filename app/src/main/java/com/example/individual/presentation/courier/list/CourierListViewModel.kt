package com.example.individual.presentation.courier.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.CourierRepository
import com.example.individual.model.Courier
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class CourierListViewModel : ViewModel() {
    val couriersLiveData = MutableLiveData<List<Courier>>()
    private val courierRepository = CourierRepository.getInstance()

    fun getCouriers(departmentId: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            courierRepository.observeCouriers(departmentId).collect {
                couriersLiveData.postValue(it)
            }
        }
        viewModelScope.launch(defaultErrorHandler) {
            courierRepository.refreshCouriers()
        }
    }
}