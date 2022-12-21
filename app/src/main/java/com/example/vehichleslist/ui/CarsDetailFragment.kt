package com.example.vehichleslist.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.vehichleslist.BaseApplication
import com.example.vehichleslist.R
import com.example.vehichleslist.databinding.FragmentCarsDetailBinding
import com.example.vehichleslist.model.Cars
import com.example.vehichleslist.network.Logo
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
    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            context, R.anim.rotate_open_anim
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            context, R.anim.rotate_close_anim
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            context, R.anim.from_bottom_animation
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            context, R.anim.to_bottom_animation
        )
    }
    private var clicked = false

    private lateinit var cars: Cars

    private var _binding: FragmentCarsDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

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

        binding.openCarsFab.setOnClickListener {
            addOnButtonClicked()
        }

        binding.deleteCarsFab.setOnClickListener {
            deleteCars(cars)
        }
    }

    private fun addOnButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            binding.editCarsFab.visibility = View.VISIBLE
            binding.deleteCarsFab.visibility = View.VISIBLE
            binding.editCarsFab.isEnabled = true
            binding.deleteCarsFab.isEnabled = true
        } else {
            binding.editCarsFab.visibility = View.GONE
            binding.deleteCarsFab.visibility = View.GONE
            binding.editCarsFab.isEnabled = false
            binding.deleteCarsFab.isEnabled = false
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            binding.editCarsFab.startAnimation(fromBottom)
            binding.deleteCarsFab.startAnimation(fromBottom)
            binding.openCarsFab.startAnimation(rotateOpen)
        } else {
            binding.editCarsFab.startAnimation(toBottom)
            binding.deleteCarsFab.startAnimation(toBottom)
            binding.openCarsFab.startAnimation(rotateClose)
        }
    }


     private fun deleteCars(cars: Cars) {
         AlertDialog.Builder(requireContext()).setTitle(R.string.confirm_delete)
             .setMessage(R.string.delete_text)
             .setPositiveButton(R.string.delete) { _, _ ->
                 viewModel.deleteCars(cars)
                 findNavController().navigate(
                     R.id.action_carsDetailFragment_to_carsListFragment
                 )
             }.setNegativeButton(R.string.undo, null).show()
     }

    @SuppressLint("SetTextI18n")
    private fun bindCars() {
        binding.apply {
            model2.text = cars.model
            name.text = cars.name
            age.text = "Age of production: " + cars.age
            type.text = "Type: " + cars.type
            fuel.text = "Type of fuel: " + cars.fuel
            chilometer.text = "Total chilometer: " + cars.chilom + " km"
            licensePlate.text = "License plate: " + cars.license
            displacement.text = "Displacement: " + cars.displac + " cc"

            editCarsFab.setOnClickListener {
                val action =
                    CarsDetailFragmentDirections.actionCarsDetailFragmentToAddCarsFragment(cars.id)
                findNavController().navigate(action)
            }
            val observer = Observer<List<Logo>> {
                setAndGetUriByBrandParsingListOfLogoAndImageView(
                    logoDataApi = viewModel.logoDataApi.value, cars.name, image as ImageView
                )
            }
            viewModel.logoDataApi.observe(viewLifecycleOwner, observer)
        }
    }
}

