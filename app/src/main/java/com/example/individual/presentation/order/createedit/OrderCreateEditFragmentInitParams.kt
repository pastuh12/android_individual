package com.example.individual.presentation.order.createedit

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderCreateEditFragmentInitParams(
    val courierId: Long,
    val id: Long? = null
) : InitParams