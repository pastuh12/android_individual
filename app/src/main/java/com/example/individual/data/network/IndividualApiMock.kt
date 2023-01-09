package com.example.individual.data.network

import com.example.individual.model.Department
import com.example.individual.model.Courier
import kotlinx.coroutines.delay

class IndividualApiMock {
    suspend fun getDepartments(): List<Department> {
        delay(1000)
        return listOf()
    }

    suspend fun addDepartment(department: Department): Department {
        delay(1000)
        return department
    }

    suspend fun updateDepartment(department: Department): Department {
        delay(1000)
        return department
    }

    suspend fun getCouriers(): List<Courier> {
        delay(1000)
        return listOf()
    }

    fun addCourier(department: Courier): Courier {
        return department
    }

    fun updateCourier(department: Courier): Courier {
        return department
    }

    suspend fun deleteDepartment(department: Department) {
        delay(1000)
    }

    suspend fun deleteCourier(courier: Courier) {
        delay(1000)
    }
}