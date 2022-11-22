package com.example.vehichleslist

import android.app.Application
import com.example.vehichleslist.data.CarDatabase

class BaseApplication : Application() {
    val database: CarDatabase by lazy { CarDatabase.getDatabase(this) }
}