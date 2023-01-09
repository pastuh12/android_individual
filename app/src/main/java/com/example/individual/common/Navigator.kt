package com.example.individual.common

import androidx.fragment.app.Fragment
import com.example.individual.model.Department
import com.example.individual.model.Courier

interface Navigator {
    fun navigateToCouriers(department: Department)
    fun navigateToCourierCreateEdit(departmentId: Long, courierId: Long? = null)
    fun navigateToDepartmentCreateEdit(id: Long? = null)
    fun exit(fragment: Fragment)
    fun navigateToOrderCreateEdit(courierId: Long, id: Long? = null)
    fun navigateToOrders(courier: Courier)
}