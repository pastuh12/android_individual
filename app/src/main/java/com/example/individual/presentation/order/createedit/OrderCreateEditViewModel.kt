package com.example.individual.presentation.order.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.OrderRepository
import com.example.individual.model.Order
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class OrderCreateEditViewModel : ViewModel() {
    val orderLiveData = MutableLiveData<Order?>()
    private val orderRepository = OrderRepository.getInstance()

    fun getOrder(id: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            orderLiveData.postValue(orderRepository.getOrderById(id))
        }
    }

    fun saveOrder(newOrder: Order) {
        viewModelScope.launch(defaultErrorHandler) {
            orderLiveData.value?.let { oldOrder ->
                orderRepository.update(newOrder.copy(id = oldOrder.id))
            } ?: orderRepository.add(newOrder)
        }
    }

    fun deleteOrder() {
        orderLiveData.value?.let { order ->
            viewModelScope.launch(defaultErrorHandler) {
                orderRepository.delete(order)
            }
        }
    }
}