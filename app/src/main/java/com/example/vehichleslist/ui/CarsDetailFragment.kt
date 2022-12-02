package com.example.vehichleslist.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.vehichleslist.BaseApplication
import com.example.vehichleslist.R
import com.example.vehichleslist.databinding.FragmentCarsDetailBinding
import com.example.vehichleslist.model.Cars
import com.example.vehichleslist.setAndGetUriByBrandParsingListOfLogoAndImageView
import com.example.vehichleslist.ui.viewmodel.CarsViewModel
import com.example.vehichleslist.ui.viewmodel.CarsViewModelFactory

class CarsDetailFragment : Fragment() {

    private val navigationArgs: AddCarsFragmentArgs by navArgs()

    private val viewModel: CarsViewModel by activityViewModels {
        CarsViewModelFactory(
            (activity?.application as BaseApplication).database.carsDao()
        )
    }
    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.from_bottom_animation) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.to_bottom_animation) }

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
        binding.deleteCarsFab.setOnClickListener {
            deleteCars(cars)
        }
    }

    private fun deleteCars(cars: Cars) {
        viewModel.deleteCars(cars)
        findNavController().navigate(
            R.id.action_carsDetailFragment_to_carsListFragment
        )
    }




    @SuppressLint("SetTextI18n")
    private fun bindCars() {
        binding.apply {
            name.text = cars.name
            model.text = "Model: " + cars.model
            age.text = "Age of production: " + cars.age
            type.text = "Model: " + cars.type
            fuel.text = "Type of fuel: " + cars.fuel
            chilometer.text = "Total chilometer: " + cars.chilom + " km"
            licensePlate.text = "License plate: " +  cars.license
            displacement.text = "Displacement: " + cars.displac + " cc"
       //     setAndGetUriByBrandParsingListOfLogoAndImageView(viewModel.logoDataApi.value, cars.name, image)
            editCarsFab.setOnClickListener {
                val action = CarsDetailFragmentDirections
                    .actionCarsDetailFragmentToAddCarsFragment(cars.id)
                findNavController().navigate(action)
            }
        }
    }
}

