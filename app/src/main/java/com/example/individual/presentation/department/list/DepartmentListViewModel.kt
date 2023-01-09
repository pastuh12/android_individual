package com.example.individual.presentation.department.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.DepartmentRepository
import com.example.individual.model.Department
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class DepartmentListViewModel : ViewModel() {
    val departmentsLiveData = MutableLiveData<List<Department>>()
    private val departmentRepository = DepartmentRepository.getInstance()

    fun getDepartments() {
        viewModelScope.launch(defaultErrorHandler) {
            departmentRepository.observeDepartments().collect {
                departmentsLiveData.postValue(it)
            }
        }
        viewModelScope.launch(defaultErrorHandler) { departmentRepository.refreshDepartments() }
    }
}