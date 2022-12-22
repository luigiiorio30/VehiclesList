package com.example.vehichleslist.ui

import android.app.AlertDialog
import android.content.DialogInterface
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

    private val regex = Regex("^[A-Za-z]{2}[0-9]{3}[A-Za-z]{2}\$")


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
        viewModel.fuelAcquisition()
        viewModel.typeAcquisition()
        val id = navigationArgs.id
        if (id > 0) {

            viewModel.getCars(id).observe(viewLifecycleOwner) {
                it?.apply {
                    cars = it
                    bindCars(cars)
                }
            }
        } else {
            binding.saveBtn.setOnClickListener {
                addCars()
            }
            binding.fuelInput.setOnClickListener {
                setFuelCar()
            }
            binding.typeInput.setOnClickListener {
                setTypeCar()
            }
        }
    }

    private fun addCars() {
        if (isValidEntry()) {
            if (isValidEntry() || isValidLicensePlate() ) {
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
            }} else {
            val toast = Toast.makeText(context, isInputEmpty(), duration)
            toast.show()
        }
    }

    private fun isInputEmpty(): Int {
        return when (error.isBlank()){
            binding.nameInput.text.toString().isBlank() -> R.string.name_error
            binding.modelInput.text.toString().isBlank() -> R.string.model_error
            binding.ageInput.text.toString().isBlank() -> R.string.age_error
            !binding.licenseInput.text.toString().matches(regex) -> R.string.plate_warning
            binding.chilometerInput.text.toString().isBlank() -> R.string.chilometer_error
            binding.displacementInput.text.toString().isBlank() -> R.string.displacement_error

            else -> R.string.age_displac_check
        }
    }


    private fun updateCars() {
        if (isValidEntry()) {
            if (isValidEntry() || isValidLicensePlate()) {
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
            }}else {
            val toast = Toast.makeText(context, isInputEmpty(), duration)
            toast.show()
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
            binding.fuelInput.setOnClickListener {
                setFuelCar()
            }
            binding.typeInput.setOnClickListener {
                setTypeCar()
            }
        }
    }

    private fun isValidEntry() = viewModel.isValidEntry(
        binding.nameInput.text.toString(),
        binding.modelInput.text.toString(),
        binding.ageInput.text.toString(),
        binding.chilometerInput.text.toString(),
        binding.licenseInput.text.toString(),
        binding.displacementInput.text.toString()
    )

    private fun isValidLicensePlate() = viewModel.isValidLicensePlate(
        binding.licenseInput.text.toString()
    )

    private fun setFuelCar() {
        val listFuelCar = viewModel.getFuel()
        val itemsFuel = arrayOfNulls<CharSequence>(listFuelCar.size)
        for (i in listFuelCar.indices) {
            itemsFuel[i] = listFuelCar[i]
        }
        val builderFuel: AlertDialog.Builder = AlertDialog.Builder(context)
        builderFuel.setTitle(R.string.fuel)
        builderFuel.setSingleChoiceItems(
            itemsFuel,
            viewModel.checkedItemFuel
        ) { _: DialogInterface, which ->
            viewModel.checkedItemFuel = which
        }
        builderFuel.setItems(itemsFuel) { _: DialogInterface, which ->
            viewModel.checkedItemFuel = which
        }
        builderFuel.setPositiveButton("Ok") { _: DialogInterface, _ ->
            binding.fuelInput.setText(itemsFuel[viewModel.checkedItemFuel].toString())
        }
        builderFuel.setNegativeButton("Cancel") { _: DialogInterface, _ ->
            binding.fuelInput.setText("")
        }
        builderFuel.show()
    }

    private fun setTypeCar() {
        val listTypeCar = viewModel.getType()
        val itemsTypes = arrayOfNulls<CharSequence>(listTypeCar.size)
        for (i in listTypeCar.indices) {
            itemsTypes[i] = listTypeCar[i]
        }
        val builderTypes: AlertDialog.Builder = AlertDialog.Builder(context)
        builderTypes.setTitle(R.string.cars_model)
        builderTypes.setSingleChoiceItems(
            itemsTypes,
            viewModel.checkedItemType
        ) { _: DialogInterface, which ->
            viewModel.checkedItemType = which
        }
        builderTypes.setItems(itemsTypes) { _: DialogInterface, which ->
            viewModel.checkedItemType = which
        }
        builderTypes.setPositiveButton("Ok") { _: DialogInterface, _ ->
            binding.typeInput.setText(itemsTypes[viewModel.checkedItemType].toString())
        }
        builderTypes.setNegativeButton("Cancel") { _: DialogInterface, _ ->
            binding.typeInput.setText("")
        }
        builderTypes.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
