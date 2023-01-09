package com.example.individual.presentation.department.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.DepartmentRepository
import com.example.individual.model.Department
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class DepartmentCreateEditViewModel : ViewModel() {
    val departmentLiveData = MutableLiveData<Department?>()
    private val departmentRepository = DepartmentRepository.getInstance()

    fun getDepartment(id: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            departmentLiveData.postValue(departmentRepository.getDepartmentById(id))
        }
    }

    fun saveDepartment(newDepartment: Department) {
        viewModelScope.launch(defaultErrorHandler) {
            departmentLiveData.value?.let { oldDepartment ->
                departmentRepository.update(
                    newDepartment.copy(id = oldDepartment.id)
                )
            } ?: departmentRepository.add(newDepartment)
        }
    }

    fun deleteDepartment() {
        departmentLiveData.value?.let { department ->
            viewModelScope.launch(defaultErrorHandler) {
                departmentRepository.delete(department)
            }
        }
    }
}