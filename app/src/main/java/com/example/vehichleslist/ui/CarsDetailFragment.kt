package com.example.vehichleslist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.vehichleslist.BaseApplication
import com.example.vehichleslist.databinding.FragmentCarsDetailBinding
import com.example.vehichleslist.model.Cars
import com.example.vehichleslist.ui.viewmodel.CarsViewModel
import com.example.vehichleslist.ui.viewmodel.CarsViewModelFactory

class CarsDetailFragment : Fragment() {

    private val navigationArgs: AddCarsFragmentArgs by navArgs()

    private val viewModel: CarsViewModel by activityViewModels {
        CarsViewModelFactory(
            (activity?.application as BaseApplication).database.carsDao()
        )
    }

    private lateinit var cars: Cars

    private var _binding: FragmentCarsDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id

        viewModel.getCars(id).observe(viewLifecycleOwner) {
            cars = it
            bindCars()
        }
    }

    private fun bindCars() {
        binding.apply {
            name.text = cars.name
            model.text = cars.model
            age.text = cars.age
            type.text = cars.type
            fuel.text = cars.fuel

            editCarsFab.setOnClickListener {
                val action = CarsDetailFragmentDirections
                    .actionCarsDetailFragmentToAddCarsFragment(cars.id)
                findNavController().navigate(action)
            }
        }
    }
}

