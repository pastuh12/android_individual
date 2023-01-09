package com.example.individual.presentation.courier.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.CourierRepository
import com.example.individual.model.Courier
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class CourierCreateEditViewModel : ViewModel() {
    val courierLiveData = MutableLiveData<Courier?>()
    private val courierRepository = CourierRepository.getInstance()

    fun getCourier(id: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            courierLiveData.postValue(courierRepository.getCourierById(id))
        }
    }

    fun saveCourier(newCourier: Courier) {
        viewModelScope.launch(defaultErrorHandler) {
            courierLiveData.value?.let { oldCourier ->
                courierRepository.update(
                    newCourier.copy(
                        id = oldCourier.id,
                        unfulfilledOrders = oldCourier.unfulfilledOrders,
                        allOrders = oldCourier.allOrders,
                    )
                )
            } ?: courierRepository.add(newCourier)
        }
    }

    fun deleteCourier() {
        courierLiveData.value?.let { courier ->
            viewModelScope.launch(defaultErrorHandler) {
                courierRepository.delete(courier)
            }
        }
    }
}