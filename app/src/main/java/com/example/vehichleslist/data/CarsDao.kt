package com.example.vehichleslist.data

import androidx.room.*
import com.example.vehichleslist.model.Cars
import kotlinx.coroutines.flow.Flow


@Dao
interface CarsDao {
    @Query("SELECT * FROM cars")
    fun getCars(): Flow<List<Cars>>

    @Query("SELECT * FROM cars WHERE id = :id")
    fun getCars(id: Long): Flow<Cars>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cars: Cars)
    @Update
    suspend fun update(cars: Cars)

    @Delete
    suspend fun delete(cars: Cars)
}