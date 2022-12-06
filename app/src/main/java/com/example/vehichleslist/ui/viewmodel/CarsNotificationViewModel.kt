package com.example.vehichleslist.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.vehichleslist.worker.CarServiceReminderWorker
import java.util.concurrent.TimeUnit

class CarsNotificationViewModel (application: Application): ViewModel(){
    private val workManager = WorkManager.getInstance(application)
    internal fun scheduleReminder(
        duration: Long,
        unit: TimeUnit,
        carName: String,
        chilom: Int,
        plateKey: String,
        carModel: String

    ) {

        if (chilom >= 90000) {
            val string = "$carName $carModel ($plateKey)"
            val data = Data.Builder().putString(CarServiceReminderWorker.nameKey, string).build()
            val carReminderBuilder = OneTimeWorkRequestBuilder<CarServiceReminderWorker>()
                .setInitialDelay(duration, unit)
                .setInputData(data)
                .build()
            workManager.enqueueUniqueWork(carName, ExistingWorkPolicy.REPLACE, carReminderBuilder)
        }
    }
}
class CarsNotificationViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarsNotificationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CarsNotificationViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}