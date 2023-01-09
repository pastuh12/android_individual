package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.Department
import kotlinx.coroutines.flow.Flow

@Dao
interface DepartmentDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getDepartments(): Flow<List<Department>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getDepartmentById(id: Long): Department

    @Update
    suspend fun updateDepartment(department: Department)

    @Delete
    suspend fun deleteDepartment(department: Department)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(department: Department)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Department>)

    companion object {
        private const val TABLE_NAME = Department.TABLE_NAME
    }
}