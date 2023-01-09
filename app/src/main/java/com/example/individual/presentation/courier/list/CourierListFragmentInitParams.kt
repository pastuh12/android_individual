package com.example.individual.presentation.courier.list

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourierListFragmentInitParams(
    val departmentId: Long,
    val departmentName: String
) : InitParams