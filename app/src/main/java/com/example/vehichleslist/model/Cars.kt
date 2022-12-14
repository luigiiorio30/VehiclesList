package com.example.vehichleslist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars_database")
data class Cars(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "brand") val name: String,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "fuel") val fuel: String,
    @ColumnInfo(name = "chilometer") val chilom: Int,
    @ColumnInfo(name = "licensePlate") val license: String,
    @ColumnInfo(name = "displacement") val displac: String,
)
