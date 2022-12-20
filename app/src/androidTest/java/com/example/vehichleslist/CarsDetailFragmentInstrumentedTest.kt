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

@RunWith(AndroidJUnit4::class)
class CarsDetailFragmentInstrumentedTest {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)
    val car = CarsDetail


    /**
     *  Function for delete a car into the database with add
     */

    //TODO: Actually working
    @Test
    fun delete_car_ok_with_add() {
        enableWifi(true)
        Thread.sleep(500)
        enableCellularData(true)
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
        Thread.sleep(3000)
        selectTypeIntoList(R.id.type_input, car[0].type)
        Thread.sleep(3000)
        selectFuelIntoList(R.id.fuel_input, car[0].fuel)
        Thread.sleep(500)
        clickTextInputWriteString(R.id.displacement_input, car[0].displac)
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
    //TODO: Actually working
    @Test
    fun delete_car_no_with_add() {
        enableWifi(true)
        Thread.sleep(500)
        enableCellularData(true)
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
        Thread.sleep(3000)
        selectTypeIntoList(R.id.type_input, car[0].type)
        Thread.sleep(3000)
        selectFuelIntoList(R.id.fuel_input, car[0].fuel)
        Thread.sleep(500)
        clickTextInputWriteString(R.id.displacement_input, car[0].displac)
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
    //TODO: Actually working
    @Test
    fun delete_car_no_without_add() {
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

    //TODO: Actually working
    @Test
    fun delete_car_ok_without_add() {
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
}