package com.example.vehichleslist

import android.app.AlertDialog
import android.content.Context
import androidx.navigation.NavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import java.util.*

@RunWith(AndroidJUnit4::class)
class CarsDetailFragmentInstrumentedTest {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)
    val car = CarsDetail.random()




    /**
     *  Function for delete a car into the database with add
     */
    //TODO: Actually working- but fix multi card selection
    @Test
    fun delete_car_with_add() {
        enableWifi(true)
        Thread.sleep(500)
        enableCellularData(true)
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
        Thread.sleep(500)
        clickId(R.id.Card)
        Thread.sleep(500)
        clickId(R.id.open_cars_fab)
        Thread.sleep(500)
        clickId(R.id.delete_cars_fab)
        Thread.sleep(3000)
        onView(withText(R.string.delete)).perform(click())
        Thread.sleep(3000)
    }

    /**
     *  Function for reject a car delete into the database with add
     */
    //TODO: Actually working- but fix multi card selection
    @Test
    fun undo_delete_car_with_add() {
        enableWifi(true)
        Thread.sleep(500)
        enableCellularData(true)
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
        Thread.sleep(500)
        clickId(R.id.Card)
        Thread.sleep(500)
        clickId(R.id.open_cars_fab)
        Thread.sleep(500)
        clickId(R.id.delete_cars_fab)
        Thread.sleep(3000)
        onView(withText(R.string.undo)).perform(click())
        Thread.sleep(500)
        pressBack()
        Thread.sleep(4000)
    }

    /**
     *  Function for reject a car delete into the database with add
     */
    //TODO: Actually working- but fix multi card selection
    @Test
    fun undo_delete_car_without_add() {
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
        onView(withText(R.string.undo)).perform(click())
        Thread.sleep(3000)
    }

    /**
     *  Function for delete a car into the database with add
     */
    //TODO: Actually working - but fix multi card selection
    @Test
    fun delete_car_without_add() {
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
        onView(withText(R.string.delete)).perform(click())
        Thread.sleep(3000)
    }

    /**
     *  Function for delete a car into the database with add
     */
    //TODO: Work in progress
    @Test
    fun modify_car() {
        enableWifi(true)
        Thread.sleep(500)
        enableCellularData(true)
        Thread.sleep(500)

        /*...*/

        clickRandom()
        Thread.sleep(500)
        clickId(R.id.open_cars_fab)
        Thread.sleep(500)
        clickId(R.id.edit_cars_fab)
        Thread.sleep(500)
        clickTextInputWriteString(R.id.age_input, car.age.toString())
        Thread.sleep(500)
        clickId(R.id.save_btn)
        Thread.sleep(3000)
    }
}