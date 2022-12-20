package com.example.vehichleslist

import androidx.navigation.NavController
import androidx.test.espresso.action.ViewActions.click
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class AddCarsListFragmentInstrumentedTest {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)
    val mockNavController = Mockito.mock(NavController::class.java)!!
    val car = CarsDetail


    /**
     * Function for adding a car by taking the information from the CarsTest list with internet
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
        Thread.sleep(500)
        /*
        clickTextInputWriteString(R.id.type_input, car[0].type)
        Thread.sleep(500)
        clickTextInputWriteString(R.id.fuel_input, car[0].fuel)
        Thread.sleep(500)
         */
        clickTextInputWriteString(R.id.displacement_input, car[0].displac)
        hideKeyboard()
        Thread.sleep(500)
        clickId(R.id.save_btn)
        Thread.sleep(3000)
    }

    /**
     * Function for adding a car by taking the information from the CarsTest list without internet
     */

    //TODO: Actually working
    fun add_new_car_with_no_internet() {
        enableWifi(false)
        Thread.sleep(500)
        enableCellularData(false)
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
}