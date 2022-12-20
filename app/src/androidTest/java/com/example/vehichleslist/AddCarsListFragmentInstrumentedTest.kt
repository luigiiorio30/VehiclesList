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
    val car = CarsDetail.random()


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
        clickTextInputWriteString(R.id.name_input, car.name)
        Thread.sleep(500)
        clickTextInputWriteString(R.id.model_input, car.model)
        Thread.sleep(500)
        clickTextInputWriteString(R.id.age_input, car.age.toString())
        Thread.sleep(500)
        clickTextInputWriteString(R.id.license_input, car.license)
        Thread.sleep(500)
        clickTextInputWriteString(R.id.chilometer_input, car.chilom.toString())
        Thread.sleep(3000)
        selectTypeIntoList(R.id.type_input, car.type)
        Thread.sleep(3000)
        selectFuelIntoList(R.id.fuel_input, car.fuel)
        Thread.sleep(500)
        clickTextInputWriteString(R.id.displacement_input, car.displac)
        hideKeyboard()
        Thread.sleep(500)
        clickId(R.id.save_btn)
        Thread.sleep(4000)
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
        clickTextInputWriteString(R.id.name_input, car.name)
        Thread.sleep(500)
        clickTextInputWriteString(R.id.model_input, car.model)
        Thread.sleep(500)
        clickTextInputWriteString(R.id.age_input, car.age.toString())
        Thread.sleep(500)
        clickTextInputWriteString(R.id.license_input, car.license)
        Thread.sleep(500)
        clickTextInputWriteString(R.id.chilometer_input, car.chilom.toString())
        hideKeyboard()
        Thread.sleep(500)
        clickId(R.id.save_btn)
        Thread.sleep(3000)
    }
}