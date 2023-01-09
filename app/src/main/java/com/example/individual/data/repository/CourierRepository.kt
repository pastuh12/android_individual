package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.Courier
import kotlinx.coroutines.flow.Flow

class CourierRepository {

    private val individualApi = NetworkProvider.get().individualApi
    private val courierDao = DatabaseProvider.get().getCourierDao()

    fun observeCouriers(departmentId: Long): Flow<List<Courier>> {
        return courierDao.getCouriers(departmentId)
    }

    suspend fun refreshCouriers() {
        val couriers = individualApi.getCouriers().map { it }
        courierDao.deleteAll()
        courierDao.insertAll(couriers)
    }

    suspend fun updateCouriers() {
        val couriers = individualApi.getCouriers().map { it }
        courierDao.updateAll(couriers)
    }

    suspend fun add(courier: Courier) {
        val courierFromServer =
            individualApi.addCourier(courier)
        courierDao.insert(courierFromServer)
    }

    suspend fun update(courier: Courier) {
        val courierFromServer =
            individualApi.updateCourier(courier.id, courier)

        courierDao.insert(courierFromServer)
    }

    suspend fun delete(courier: Courier) {
        individualApi.deleteCourier(courier.id)
        courierDao.delete(courier)
    }

    suspend fun getCourierById(id: Long): Courier {
        return courierDao.getById(id)
    }

    companion object {
        private var INSTANCE: CourierRepository? = null
        fun getInstance(): CourierRepository {
            if (INSTANCE == null) {
                INSTANCE = CourierRepository()
            }
            return INSTANCE ?: throw IllegalStateException("CageRepository is not init")
        }
    }
}