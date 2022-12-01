package com.example.vehichleslist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.vehichleslist.BaseApplication
import com.example.vehichleslist.R
import com.example.vehichleslist.databinding.FragmentCarListBinding
import com.example.vehichleslist.network.Logo
import com.example.vehichleslist.ui.adapter.CarsListAdapter
import com.example.vehichleslist.ui.viewmodel.CarsViewModel
import com.example.vehichleslist.ui.viewmodel.CarsViewModelFactory

class CarsListFragment : Fragment() {

    private val viewModel: CarsViewModel by activityViewModels {
        CarsViewModelFactory(
            (activity?.application as BaseApplication).database.carsDao()
        )
    }
    private var _binding: FragmentCarListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLogo()

        val adapter =
            CarsListAdapter(
                logoDataApi = viewModel.logoDataApi.value,
                clickListener = { cars ->
                    val action = CarsListFragmentDirections
                        .actionCarsListFragmentToCarsDetailFragment(cars.id)
                    findNavController().navigate(action)
                })
        val observer = Observer<List<Logo>>{
            binding.recyclerView.adapter = adapter
        }
        viewModel.logoDataApi.observe(viewLifecycleOwner, observer)
        viewModel.cars.observe(this.viewLifecycleOwner) { cars ->
            cars.let { adapter.submitList(it) }
        }

        binding.apply {
            recyclerView.adapter = adapter
            addCarsFab.setOnClickListener {
                findNavController().navigate(
                    R.id.action_CarsListFragment_to_addCarsFragment
                )
            }
        }
    }
}
