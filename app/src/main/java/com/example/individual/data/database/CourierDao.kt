package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.Courier
import kotlinx.coroutines.flow.Flow

@Dao
interface CourierDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE  departmentId = (:departmentId)")
    fun getCouriers(departmentId: Long): Flow<List<Courier>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getById(id: Long): Courier

    @Update
    suspend fun update(courier: Courier)

    @Update
    suspend fun updateAll(list: List<Courier>)

    @Delete
    suspend fun delete(courier: Courier)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(courier: Courier)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Courier>)

    companion object {
        private const val TABLE_NAME = Courier.TABLE_NAME
    }
}