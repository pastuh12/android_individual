package com.example.individual.presentation.order.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.OrderRepository
import com.example.individual.model.Order
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class OrderListViewModel : ViewModel() {
    val ordersLiveData = MutableLiveData<List<Order>>()
    private val orderRepository = OrderRepository.getInstance()

    fun getOrders(departmentId: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            orderRepository.observeOrders(departmentId).collect {
                ordersLiveData.postValue(it)
            }
        }
        viewModelScope.launch(defaultErrorHandler) {
            orderRepository.refreshOrders()
        }
    }
}