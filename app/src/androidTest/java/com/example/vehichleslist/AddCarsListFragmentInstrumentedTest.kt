package com.example.vehichleslist

import androidx.navigation.NavController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vehichleslist.model.Cars
import com.example.vehichleslist.ui.AddCarsFragment
import com.example.vehichleslist.ui.CarsDetailFragment
import com.example.vehichleslist.ui.viewmodel.CarsViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
class AddCarsListFragmentInstrumentedTest {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)
    val mockNavController = Mockito.mock(NavController::class.java)!!
    val car = CarsDetail


    /**
     * Function for adding a car by taking the information from the CarsTest list
     */

    //TODO: Actually working
    @Test
    fun add_new_car_with_internet() {
        enableWifi(true)
        Thread.sleep(500)
        enableCellularData(true)
        Thread.sleep(500)
        clickId(R.id.add_cars_fab)
        Thread.sleep(500)
        clickTextInputWriteString(R.id.name_input, car[0].name)
        Thread.sleep(500)
        clickTextInputWriteString(R.id.model_input, car[0].model)
        Thread.sleep(500)
        clickTextInputWriteString(R.id.age_input, car[0].age.toString())
        Thread.sleep(500)
        clickTextInputWriteString(R.id.license_input, car[0].license)
        Thread.sleep(500)
        clickTextInputWriteString(R.id.chilometer_input, car[0].chilom.toString())
        hideKeyboard()
        Thread.sleep(500)
        clickId(R.id.save_btn)
        Thread.sleep(3000)
    }


    /**
     *  Function for delete a car into the database
     */

    //TODO: Work in progress
    @Test
    fun delete_car() {
        enableWifi(true)
        Thread.sleep(500)
        enableCellularData(true)
        Thread.sleep(500)
        clickId(R.id.Card)
        Thread.sleep(500)
        clickId(R.id.open_cars_fab)
        Thread.sleep(500)
        clickId(R.id.delete_cars_fab)
        Thread.sleep(3000)
    }

    /**
     *  Function for check if description and image background are visible with no internet
     */

    //TODO: Actually working
    @Test
    fun no_internet_with_no_cars() {
        enableWifi(false)
        Thread.sleep(1000)
        enableCellularData(false)
        Thread.sleep(1000)
        checkIfVisible(R.id.description)
        Thread.sleep(1000)
        checkIfVisible(R.id.image)
        Thread.sleep(1000)
        enableWifi(true)
        Thread.sleep(1000)
        enableCellularData(true)
        Thread.sleep(3000)
    }
}