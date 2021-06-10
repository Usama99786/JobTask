package com.example.jobtask

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobtask.databinding.ActivityMainBinding
import com.example.jobtask.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainFragment : Fragment() {
    lateinit var viewModel: CategoryViewModel
     var _binding:FragmentMainBinding? = null
    var navController : NavController?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MainActivity.binding.navView.visibility=View.VISIBLE
        navController= Navigation.findNavController(view)

        binding.categoriesRecycler.layoutManager= GridLayoutManager(activity,3)
        binding.categoriesRecycler.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.trendingProuctsRecycler.layoutManager=linearLayoutManager
        binding.categoriesRecycler.setHasFixedSize(true)

        viewModel = ViewModelProvider(
            this
        ).get(CategoryViewModel::class.java)
        viewModel.getCategories()
        viewModel.getTrendingProducts()
        viewModel.categoriesArray.observe(viewLifecycleOwner, Observer {
              binding.categoriesRecycler.adapter= activity?.let { it1 -> CategoryAdapter(it, it1) }
        } )
        viewModel.productsArray.observe(viewLifecycleOwner, Observer {
           binding.trendingProuctsRecycler.adapter= activity?.let { it1 -> ProductAdapter(it1,it,
               navController!!,viewModel
           ) }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}