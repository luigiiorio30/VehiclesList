package com.example.vehichleslist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat

@Entity(tableName = "cars_database")
data class Cars(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "brand") val name: String,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "age") val age: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "fuel") val fuel: String,
    @ColumnInfo(name = "chilometer") val chilom: String,
    @ColumnInfo(name = "licensePlate") val license: String,
    @ColumnInfo(name = "displacement") val displac: String,
)

//fun Cars.getFormattedPrice(): String = NumberFormat.getCurrencyInstance().format(price)
