package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.Order
import kotlinx.coroutines.flow.Flow

class OrderRepository {

    private val individualApi = NetworkProvider.get().individualApi
    private val orderDao = DatabaseProvider.get().getOrderDao()
    private val courierRepository = CourierRepository.getInstance()

    fun observeOrders(orderId: Long): Flow<List<Order>> {
        return orderDao.getAll(orderId)
    }

    suspend fun refreshOrders() {
        val orders = individualApi.getOrders().map { it }
        orderDao.deleteAll()
        orderDao.insertAll(orders)
    }

    suspend fun add(newOrder: Order) {
        val order =
            individualApi.addOrder(newOrder)
        orderDao.insert(order)
        courierRepository.updateCouriers()
    }

    suspend fun update(updatedOrder: Order) {
        val order =
            individualApi.updateOrder(
                updatedOrder.id,
                updatedOrder
            )
        orderDao.insert(order)
        courierRepository.updateCouriers()
    }

    suspend fun delete(order: Order) {
        individualApi.deleteOrder(order.id)
        orderDao.delete(order)
        courierRepository.updateCouriers()
    }

    suspend fun getOrderById(id: Long): Order {
        return orderDao.getById(id)
    }

    companion object {
        private var INSTANCE: OrderRepository? = null
        fun getInstance(): OrderRepository {
            if (INSTANCE == null) {
                INSTANCE = OrderRepository()
            }
            return INSTANCE ?: throw IllegalStateException("CageRepository is not init")
        }
    }
}