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
        checkIfVisible(R.id.description)
        Thread.sleep(500)
        checkIfVisible(R.id.image)
        Thread.sleep(500)
        enableWifi(true)
        Thread.sleep(500)
        enableCellularData(true)
        Thread.sleep(4000)
    }
}