package com.example.vehichleslist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vehichleslist.model.Cars
import kotlinx.coroutines.internal.synchronized


@Database(entities = [Cars::class], version = 1, exportSchema = false)
abstract class CarDatabase : RoomDatabase() {
    abstract fun carsDao(): CarsDao

    companion object {

        private var INSTANCE: CarDatabase? = null

        fun getDatabase(context: Context): CarDatabase {
            return INSTANCE ?: kotlin.synchronized(context) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarDatabase::class.java,
                    "car_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
