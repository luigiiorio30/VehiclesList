package com.example.vehichleslist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Cars(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "brand") val name: String,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "price") val price: Int,
)
