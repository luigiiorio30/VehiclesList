package com.example.vehichleslist

import androidx.navigation.NavController
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class CarsListFragmentInstrumentedTest {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)
    val car = CarsDetail

    /**
     *  Function for check if description and image background are visible with no cars
     */

    //TODO: Actually working
    @Test
    fun no_vehicles_in_database_alert() {
        enableWifi(false)
        Thread.sleep(1000)
        enableCellularData(false)
        Thread.sleep(1000)
        clickId(R.id.Card)
        Thread.sleep(500)
        clickId(R.id.open_cars_fab)
        Thread.sleep(500)
        clickId(R.id.delete_cars_fab)
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withText(R.string.delete)).perform(ViewActions.click())
        Thread.sleep(3000)
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