package com.example.vehichleslist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.vehichleslist.BaseApplication
import com.example.vehichleslist.R
import com.example.vehichleslist.databinding.FragmentAddCarBinding
import com.example.vehichleslist.model.Cars
import com.example.vehichleslist.ui.viewmodel.CarsViewModel
import com.example.vehichleslist.ui.viewmodel.CarsViewModelFactory

class AddCarsFragment : Fragment() {

    private val navigationArgs: AddCarsFragmentArgs by navArgs()

    private var _binding: FragmentAddCarBinding? = null

    private lateinit var cars: Cars

    private val binding get() = _binding!!

    private val viewModel: CarsViewModel by activityViewModels {
        CarsViewModelFactory(
            (activity?.application as BaseApplication).database.carsDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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

    private fun addCars() {
        if (isValidEntry()) {
            viewModel.addCars(
                binding.nameInput.text.toString(),
                binding.modelInput.text.toString(),
                binding.ageInput.text.toString(),
                binding.typeInput.text.toString(),
                binding.fuelInput.text.toString(),
            )
                findNavController().navigate(
                    R.id.action_addCarsFragment_to_CarsListFragment
                )
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
            ageInput.setText(cars.age, TextView.BufferType.SPANNABLE)
            typeInput.setText(cars.type, TextView.BufferType.SPANNABLE)
            fuelInput.setText(cars.fuel, TextView.BufferType.SPANNABLE)
            saveBtn.setOnClickListener {
                updateCars()
            }
        }
    }

    private fun isValidEntry() = viewModel.isValidEntry(
        binding.nameInput.text.toString(),
        binding.modelInput.text.toString()
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}