package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.Order
import kotlinx.coroutines.flow.Flow

@Dao
interface
OrderDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE  courierId = (:courierId)")
    fun getAll(courierId: Long): Flow<List<Order>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getById(id: Long): Order

    @Update
    suspend fun update(order: Order)

    @Update
    suspend fun updateAll(ist: List<Order>)

    @Delete
    suspend fun delete(order: Order)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: Order)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Order>)

    companion object {
        private const val TABLE_NAME = Order.TABLE_NAME
    }
}