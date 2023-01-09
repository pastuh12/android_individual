package com.example.individual.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = Order.TABLE_NAME,
    foreignKeys =
    [ForeignKey(
        entity = Courier::class,
        parentColumns = ["id"],
        childColumns = ["courierId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
data class Order(
    @PrimaryKey
    val id: Long,
    val courierId: Long,
    val address: String,
    val isfulFilled: Boolean,
    val prise: Int,
) {
    companion object {
        const val TABLE_NAME = "order1"
    }
}