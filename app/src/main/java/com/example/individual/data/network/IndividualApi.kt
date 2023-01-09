package com.example.individual.data.network

import com.example.individual.model.Department
import com.example.individual.model.Courier
import com.example.individual.model.Order
import retrofit2.Response
import retrofit2.http.*


interface IndividualApi {

    @GET("departments")
    suspend fun getDepartments(): List<Department>

    @POST("departments")
    suspend fun addDepartment(@Body department: Department): Department

    @PATCH("departments/{id}")
    suspend fun updateDepartment(@Path("id") id: Long, @Body department: Department): Department

    @DELETE("departments/{id}")
    suspend fun deleteDepartment(@Path("id") id: Long): Response<Unit>

    @GET("couriers")
    suspend fun getCouriers(): List<Courier>

    @POST("couriers")
    suspend fun addCourier(@Body courier: Courier): Courier

    @PATCH("couriers/{id}")
    suspend fun updateCourier(
        @Path("id") id: Long,
        @Body courier: Courier
    ): Courier

    @DELETE("couriers/{id}")
    suspend fun deleteCourier(@Path("id") id: Long): Response<Unit>

    @GET("orders")
    suspend fun getOrders(): List<Order>

    @POST("orders")
    suspend fun addOrder(@Body order: Order): Order

    @PATCH("orders/{id}")
    suspend fun updateOrder(
        @Path("id") id: Long,
        @Body order: Order
    ): Order

    @DELETE("orders/{id}")
    suspend fun deleteOrder(@Path("id") id: Long): Response<Unit>
}