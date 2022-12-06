package com.example.vehichleslist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.vehichleslist.BaseApplication
import com.example.vehichleslist.R
import com.example.vehichleslist.databinding.FragmentAddCarBinding
import com.example.vehichleslist.model.Cars
import com.example.vehichleslist.ui.viewmodel.CarsNotificationViewModel
import com.example.vehichleslist.ui.viewmodel.CarsNotificationViewModelFactory
import com.example.vehichleslist.ui.viewmodel.CarsViewModel
import com.example.vehichleslist.ui.viewmodel.CarsViewModelFactory
import java.util.concurrent.TimeUnit


class AddCarsFragment : Fragment() {

    private val navigationArgs: AddCarsFragmentArgs by navArgs()

    private var _binding: FragmentAddCarBinding? = null

    private lateinit var cars: Cars

    private val error = ""

    private val duration = Toast.LENGTH_SHORT

    private val binding get() = _binding!!

    private val viewModel: CarsViewModel by activityViewModels {
        CarsViewModelFactory(
            (activity?.application as BaseApplication).database.carsDao()
        )
    }

    private val viewModelNotification: CarsNotificationViewModel by viewModels {
        CarsNotificationViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddCarBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        if (id > 0) {

            viewModel.getCars(id).observe(viewLifecycleOwner) {
                it?.apply {
                    cars = it
                    bindCars(cars)
                }
            }

            binding.deleteBtn.visibility = View.VISIBLE
            binding.deleteBtn.setOnClickListener {
                deleteCars(cars)
            }
        } else {
            binding.saveBtn.setOnClickListener {
                addCars()
            }
        }
    }


    private fun deleteCars(cars: Cars) {
        viewModel.deleteCars(cars)
        findNavController().navigate(
            R.id.action_addCarsFragment_to_CarsListFragment
        )
    }

    //TODO: Add confirm for vehicle deleting

    private fun addCars() {
        if (isValidEntry()) {
            viewModel.addCars(
                binding.nameInput.text.toString(),
                binding.modelInput.text.toString(),
                binding.ageInput.text.toString(),
                binding.typeInput.text.toString(),
                binding.fuelInput.text.toString(),
                binding.chilometerInput.text.toString(),
                binding.licenseInput.text.toString().uppercase(),
                binding.displacementInput.text.toString()
            )
            viewModelNotification.scheduleReminder(
                5,
                TimeUnit.SECONDS,
                binding.nameInput.text.toString().uppercase(),
                binding.chilometerInput.text.toString().toInt(),
                binding.licenseInput.text.toString().uppercase(),
                binding.modelInput.text.toString(),
            )
            findNavController().navigate(
                R.id.action_addCarsFragment_to_CarsListFragment
            )
        } else {
            val toast = Toast.makeText(context, isInputEmpty(), duration)
            toast.show()
        }
    }

   private fun isInputEmpty(): Int{
        return when (error.isBlank()){
            binding.nameInput.text.toString().isBlank() -> R.string.name_error
            binding.modelInput.text.toString().isBlank() -> R.string.model_error
            binding.ageInput.text.toString().isBlank() -> R.string.age_error
            binding.chilometerInput.text.toString().isBlank() -> R.string.chilometer_error
            else -> R.string.toast_error
        }
    }

    private fun updateCars() {
        if (isValidEntry()) {
            viewModel.updateCars(
                id = navigationArgs.id,
                name = binding.nameInput.text.toString(),
                model = binding.modelInput.text.toString(),
                age = binding.ageInput.text.toString(),
                type = binding.typeInput.text.toString(),
                fuel = binding.fuelInput.text.toString(),
                chilom = binding.chilometerInput.text.toString(),
                license = binding.licenseInput.text.toString().uppercase(),
                displac = binding.displacementInput.text.toString()
            )
            viewModelNotification.scheduleReminder(
                5,
                TimeUnit.SECONDS,
                binding.nameInput.text.toString().uppercase(),
                binding.chilometerInput.text.toString().toInt(),
                binding.licenseInput.text.toString().uppercase(),
                binding.modelInput.text.toString(),
            )
            findNavController().navigate(
                R.id.action_addCarsFragment_to_CarsListFragment
            )
        }
    }

    private fun bindCars(cars: Cars) {
        binding.apply {
            nameInput.setText(cars.name, TextView.BufferType.SPANNABLE)
            modelInput.setText(cars.model, TextView.BufferType.SPANNABLE)
            ageInput.setText(cars.age.toString(), TextView.BufferType.SPANNABLE)
            typeInput.setText(cars.type, TextView.BufferType.SPANNABLE)
            fuelInput.setText(cars.fuel, TextView.BufferType.SPANNABLE)
            chilometerInput.setText(cars.chilom.toString(), TextView.BufferType.SPANNABLE)
            licenseInput.setText(cars.license, TextView.BufferType.SPANNABLE)
            displacementInput.setText(cars.displac, TextView.BufferType.SPANNABLE)
            saveBtn.setOnClickListener {
                updateCars()
            }
        }
    }

    private fun isValidEntry() = viewModel.isValidEntry(
        binding.nameInput.text.toString(),
        binding.modelInput.text.toString(),
        binding.ageInput.text.toString(),
        binding.chilometerInput.text.toString(),

    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

