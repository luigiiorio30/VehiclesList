package com.example.vehichleslist.ui.viewmodel

import android.widget.Toast
import androidx.lifecycle.*
import com.example.vehichleslist.data.CarsDao
import com.example.vehichleslist.model.Cars
import com.example.vehichleslist.network.Logo
import com.example.vehichleslist.network.LogoApi
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch

enum class LogoApiStatus { LOADING, ERROR, DONE }
class CarsViewModel(private val carsDao: CarsDao) : ViewModel() {


    // Status Logo Api
    private val _statusLogApi = MutableLiveData<LogoApiStatus>()
    // val statusLogApi: LiveData<LogoApiStatus> = _statusLogApi

    private val _logoDataApi = MutableLiveData<List<Logo>>()

    val logoDataApi: LiveData<List<Logo>> = _logoDataApi

    init {
        getLogo()
    }

    val cars: LiveData<List<Cars>> = carsDao.getCars().asLiveData()
    fun getCars(id: Long): LiveData<Cars> {
        return carsDao.getCars(id).asLiveData()
    }

    fun getLogo() {
        viewModelScope.launch {
            _statusLogApi.value = LogoApiStatus.LOADING
            try {
                _logoDataApi.value = LogoApi.retrofitService.getLogo()
                _statusLogApi.value = LogoApiStatus.DONE
            } catch (e: java.lang.Exception) {
                _statusLogApi.value = LogoApiStatus.ERROR
                _logoDataApi.value = listOf()
            }
        }
    }


    fun addCars(
        name: String,
        model: String,
        age: String,
        type: String,
        fuel: String,
        chilom: String,
        license: String,
        displac: String
    ) {
        val cars = Cars(
            name = name,
            model = model,
            age = age.toInt(),
            type = type,
            fuel = fuel,
            chilom = chilom.toInt(),
            license = license,
            displac = displac
        )
        viewModelScope.launch(Dispatchers.IO) {
            carsDao.insert(cars)
        }

    }

    fun updateCars(
        id: Long,
        name: String,
        model: String,
        age: String,
        type: String,
        fuel: String,
        chilom: String,
        license: String,
        displac: String
    ) {
        val cars = Cars(
            id = id,
            name = name,
            model = model,
            age = age.toInt(),
            type = type,
            fuel = fuel,
            chilom = chilom.toInt(),
            license = license,
            displac = displac
        )
        viewModelScope.launch(Dispatchers.IO) {
            carsDao.update(cars)
        }
    }

    fun deleteCars(cars: Cars) {
        viewModelScope.launch(Dispatchers.IO) {
            carsDao.delete(cars)
        }
    }

    fun isValidEntry(name: String, model: String, age: String, chilom: String): Boolean {
        if ((name.isBlank() || model.isBlank() || age.isBlank() || chilom.isBlank())) {
            return false
        }
        return true
    }

}

class CarsViewModelFactory(private val carsDao: CarsDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return CarsViewModel(carsDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}