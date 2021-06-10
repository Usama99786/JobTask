package com.example.jobtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.example.jobtask.databinding.FragmentMainBinding
import com.example.jobtask.databinding.FragmentProductDetailsBinding


class ProductDetailsFragment : Fragment() {
    var _binding: FragmentProductDetailsBinding? = null
    var navController : NavController?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      
        MainActivity.binding.navView.visibility=View.INVISIBLE

        binding.productNameTxt.setText(ProductAdapter.name)
        binding.rentTxt.setText(ProductAdapter.price)
        Glide.with(this).load(ProductAdapter.image).placeholder(R.drawable.bed_img).into(binding.productImg)
        binding.backImg.setOnClickListener{
          requireActivity().onBackPressed()
        }
        binding.backTxt.setOnClickListener{
            requireActivity().onBackPressed()
        }
       // binding.productNameTxt.setText(bundle.getString("product_name"))
    }


}