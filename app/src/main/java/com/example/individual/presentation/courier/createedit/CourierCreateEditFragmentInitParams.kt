package com.example.individual.presentation.courier.createedit

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourierCreateEditFragmentInitParams(
    val departmentId: Long,
    val id: Long? = null
) : InitParams