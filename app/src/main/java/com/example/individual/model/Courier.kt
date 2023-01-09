package com.example.individual.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = Courier.TABLE_NAME,
    foreignKeys =
    [ForeignKey(
        entity = Department::class,
        parentColumns = ["id"],
        childColumns = ["departmentId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
data class Courier(
    @PrimaryKey val id: Long,
    val departmentId: Long,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val deliveryMethod: String,
    val unfulfilledOrders: Int,
    val allOrders: Int,
) {
    companion object {
        const val TABLE_NAME = "courier"
    }
}