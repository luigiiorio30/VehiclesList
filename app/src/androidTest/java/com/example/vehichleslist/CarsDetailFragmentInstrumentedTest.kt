package com.example.vehichleslist

import android.app.AlertDialog
import android.content.Context
import androidx.navigation.NavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
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
    val mockNavController = Mockito.mock(NavController::class.java)!!
    val context = ApplicationProvider.getApplicationContext<Context>()
    val car = CarsDetail


    /**
     *  Function for delete a car into the database
     */

    //TODO: Actually working
    @Test
    fun delete_car_ok() {
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
        onView(withText("Delete")).perform(click())
        Thread.sleep(3000)
    }

    /**
     *  Function for reject a car delete into the database
     */
    //TODO: Actually working
    @Test
    fun delete_car_no() {
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
        onView(withText("Cancel")).perform(click())
        Thread.sleep(3000)
    }
}