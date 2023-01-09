package com.example.individual.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.individual.model.Department
import com.example.individual.model.Courier
import com.example.individual.model.Order

@Database(
    entities =
    [
        Department::class,
        Courier::class,
        Order::class
    ],
    version = 26
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun departmentDao(): DepartmentDao
    abstract fun courierDao(): CourierDao
    abstract fun orderDao(): OrderDao

    companion object {
        const val DB_NAME = "individual_database"
    }
}