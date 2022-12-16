package com.example.vehichleslist.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.vehichleslist.data.CarsDao
import com.example.vehichleslist.model.Cars
import com.example.vehichleslist.network.Logo
import com.example.vehichleslist.network.LogoApi
import com.example.vehichleslist.network.fuel.FuelApi
import com.example.vehichleslist.network.fuel.FuelInfo
import com.example.vehichleslist.network.types.CarTypeApi
import com.example.vehichleslist.network.types.TypeInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

enum class LogoApiStatus { LOADING, ERROR, DONE }
class CarsViewModel(private val carsDao: CarsDao) : ViewModel() {

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    var checkedItemFuel = -1

    var checkedItemType = -1

    private val _fuel = MutableLiveData<List<FuelInfo>>()

    val fuel: LiveData<List<FuelInfo>>
        get() = _fuel

    private val _type = MutableLiveData<List<TypeInfo>>()

    val type: LiveData<List<TypeInfo>>
        get() = _type

    private val _statusLogApi = MutableLiveData<LogoApiStatus>()

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

    /**
     * Function for acquiring fuel data via API
     */
    fun fuelAcquisition() = CoroutineScope(Dispatchers.Main).launch {
        try {
            if (_fuel.value == null) {
                _fuel.postValue(FuelApi.retrofitService.getFuelInfo())
            }

            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false

        } catch (networkError: IOException) {
            _eventNetworkError.value = true
        }
    }

    /**
     * Function to save fuel fuels in a list by API
     */
    fun getFuel(): List<String> {
        return _fuel.value!!.map { e -> e.nameFuel }.distinct()
    }


    /**
     * Function for acquiring type data via API
     */
    fun typeAcquisition() = CoroutineScope(Dispatchers.Main).launch {
        try {
            if (_type.value == null) {
                _type.postValue(CarTypeApi.retrofitService.getCarTypeInfo())
            }

            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false

        } catch (networkError: IOException) {
            _eventNetworkError.value = true
        }
    }

    /**
     * Function to save cars type in a list by API
     */
    fun getType(): List<String> {
        return _type.value!!.map { e -> e.nameType }.distinct()
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
        try {
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
        } catch (e: Exception) {
            Log.e("Test", "error", e)
        }

    }

    fun deleteCars(cars: Cars) {
        viewModelScope.launch(Dispatchers.IO) {
            carsDao.delete(cars)
        }
    }

    fun isValidEntry(name: String, model: String, age: String, chilom: String, licensePlate: String): Boolean {
        if ((name.isBlank() || model.isBlank() || age.isBlank() || chilom.isBlank() || licensePlate.isBlank())) {
            return false
        }
        return isValidLicensePlate(licensePlate)
    }

    fun isValidLicensePlate(licensePlate: String): Boolean {
        if (licensePlate.matches("^[A-Za-z]{2}[0-9]{3}[A-Za-z]{2}\$".toRegex())) {
            return true
        }
        return false
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