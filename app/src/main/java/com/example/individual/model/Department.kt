package com.example.individual.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Department.TABLE_NAME)
data class Department(
    val name: String,
    @PrimaryKey val id: Long
) {
    companion object {
        const val TABLE_NAME = "department"
    }
}
