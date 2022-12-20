package com.example.vehichleslist

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertEquals
import org.hamcrest.Matchers.hasToString
import org.hamcrest.Matchers.startsWith


fun goToAddFragmentFromHome() {
    onView(withId(R.id.action_carsDetailFragment_to_addCarsFragment))
        .perform(click())
}

fun goToHomeFromAddFragment() {
    onView(withId(R.id.action_addCarsFragment_to_CarsListFragment))
        .perform(click())
}

fun goToDetailFromHome() {
    onView(withId(R.id.action_CarsListFragment_to_carsDetailFragment))
        .perform(click())
}

fun goToAddFromDetailFragment() {
    onView(withId(R.id.action_carsDetailFragment_to_addCarsFragment))
        .perform(click())
}

fun goToHome() {
    onView(withId(R.id.carsListFragment))
        .perform(click())
}

fun goToAdd() {
    onView(withId(R.id.addCarsFragment))
        .perform(click())
}

fun goToSave(){
    onView(withId(R.id.save_btn))
        .perform(click())
}

fun goToDetail() {
    onView(withId(R.id.carsDetailFragment))
        .perform(click())
}

fun clickAddNewCar() {
    onView(withId(R.id.add_cars_fab))
        .perform(click())
}

fun clickEditFloatingDetail() {
    onView(withId(R.id.edit_cars_fab))
        .perform(click())
}

fun clickDeleteFloatingDetail() {
    onView(withId(R.id.delete_cars_fab))
        .perform(click())
}

fun clickId(idInput: Int) {
    onView(withId(idInput))
        .perform(click())
}

fun navigateToListToAdd() {
    goToHome()
    clickAddNewCar()
    goToAdd()
}

fun navigateToListToDetail() {
    goToHome()
    goToDetail()
}

fun navigateToListToDetailEdit() {
    goToHome()
    goToDetail()
    clickEditFloatingDetail()
}

fun navigateToListToDetailShare() {
    goToHome()
    goToDetail()
}

fun navigateToListToDetailDelete() {
    goToHome()
    goToDetail()
    clickDeleteFloatingDetail()
}

fun addNewCar() {
    navigateToListToAdd()
}

fun updateCar() {
    navigateToListToDetailEdit()
}

fun shareCar() {
    navigateToListToDetailShare()
}

fun deleteCar() {
    navigateToListToDetailDelete()
}

fun clickTextInputWriteString(idInput: Int, string: String) {
    onView(withId(idInput))
        .perform(typeText(string))
}


fun clickTextInputListBrand(idInput: Int, textToSearch: String) {
    clickId(idInput)
    onView(withId(R.id.name)).perform(click())
    onView(withText(textToSearch)).perform(click())
}

fun clickTextInputListModel(idInput: Int, textToSearch: String) {
    clickId(idInput)
    onData(hasToString(startsWith(textToSearch))).inAdapterView(withId(R.id.model))
        .perform(click())
}

fun clickTextInputListFuel(idInput: Int, textToSearch: String) {
    clickId(idInput)
    onData(hasToString(startsWith(textToSearch))).inAdapterView(withId(R.id.fuel))
        .perform(click())
}

fun selectFuelIntoList (idInput: Int, fuel: String){
    clickId(idInput)
    onView(withText(fuel)).perform(click())
    onView(withText("OK")).perform(click())

}

fun selectTypeIntoList (idInput: Int, type: String){
    clickId(idInput)
    onView(withText(type)).perform(click())
    onView(withText("OK")).perform(click())

}

fun enableWifi(enable: Boolean) {
    when (enable) {
        true -> InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc wifi enable")
        false -> InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc wifi disable")
    }
}


fun enableCellularData(enable: Boolean) {
    when (enable) {
        true -> InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc data enable")
        false -> InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc data disable")
    }
}


fun checkIfVisible(id: Int) {
    onView(withId(id)).check(matches(isDisplayed()))
}


fun check_current_location_is_expected(currentLocation: Int?, expectedLocation: Int) {
    assertEquals(
        currentLocation,
        expectedLocation
    )
}

fun clickItem(idCard: Int) {
    onView(withId(R.id.recycler_view)).perform(
        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(idCard, click())
    )
}


fun hideKeyboard() {
    onView(ViewMatchers.isRoot()).perform(closeSoftKeyboard())
}

fun scrollTo(id: Int) {
    onView(withId(id))
        .perform(scrollTo())
        .check(matches(isDisplayed()))
}