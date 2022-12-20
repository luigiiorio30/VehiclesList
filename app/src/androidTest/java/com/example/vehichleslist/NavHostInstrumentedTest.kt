package com.example.vehichleslist

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.vehichleslist.ui.AddCarsFragment
import com.example.vehichleslist.ui.CarsListFragment
import org.mockito.Mockito


val mockNavController: NavController = Mockito.mock(NavController::class.java)

/**
 * Function that returns a fragment and a simulated NavController object
 */
fun mockAddCarFragment(): NavController {
    launchFragmentInContainer() {
        AddCarsFragment().also { fragment ->
            fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    Navigation.setViewNavController(fragment.requireView(), mockNavController)
                }
            }
        }
    }
    return mockNavController
}

/**
 * Function that starts from the main screen
 */
fun startCarListFragment(): NavController {
    launchFragmentInContainer<CarsListFragment>() {
        CarsListFragment().also { fragment ->
            fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    Navigation.setViewNavController(fragment.requireView(), mockNavController)
                }
            }
        }
    }
    return mockNavController
}