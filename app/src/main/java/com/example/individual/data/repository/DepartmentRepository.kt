package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.Department
import kotlinx.coroutines.flow.Flow

class DepartmentRepository {
    private val individualApi = NetworkProvider.get().individualApi
    private val departmentDao = DatabaseProvider.get().getDepartmentDao()

    fun observeDepartments(): Flow<List<Department>> {
        return departmentDao.getDepartments()
    }

    suspend fun getDepartmentById(id: Long): Department {
        return departmentDao.getDepartmentById(id)
    }

    suspend fun refreshDepartments() {
        val departments = individualApi.getDepartments()
        departmentDao.deleteAll()
        departmentDao.insertAll(departments)
    }

    suspend fun add(department: Department) {
        val departmentFromServer = individualApi.addDepartment(department)
        departmentDao.insert(departmentFromServer)
    }

    suspend fun update(department: Department) {
        val departmentFromServer = individualApi.updateDepartment(department.id, department)
        departmentDao.insert(departmentFromServer)
    }

    suspend fun delete(department: Department) {
        individualApi.deleteDepartment(department.id)
        departmentDao.deleteDepartment(department)
    }

    companion object {
        private var INSTANCE: DepartmentRepository? = null
        fun getInstance(): DepartmentRepository {
            if (INSTANCE == null) {
                INSTANCE = DepartmentRepository()
            }
            return INSTANCE ?: throw IllegalStateException("CageRepository is not init")
        }
    }
}