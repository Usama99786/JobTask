package com.example.jobtask

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(var application: FragmentActivity,var productsList:List<ProductsModel>,var navController: NavController,var viewModel:CategoryViewModel):

    RecyclerView.Adapter<ProductAdapter.ProductsHolder>() {
    companion object {
        lateinit var name: String
        lateinit var price: String
        lateinit var image: String
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ProductsHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.products_items, parent, false) as View
        return ProductAdapter.ProductsHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductsHolder, position: Int) {
        val currentPostion=productsList[position]
          holder.productPriceTxt.setText(currentPostion.product_price)
          holder.productNameTxt.setText(currentPostion.product_name)
        Glide.with(application).load(currentPostion.product_img).placeholder(R.drawable.bed_img).into(holder.productImg)
        holder.productCard.setOnClickListener{

            name=currentPostion.product_name
            price=currentPostion.product_price
            image=currentPostion.product_img
            navController.navigate(R.id.action_mainFragment_to_productDetailsFragment);
//            navController!!.navigate(R.id.action_mainFragment_to_productDetailsFragment)
        }

    }

    override fun getItemCount(): Int {
       return productsList.size
    }
    class ProductsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImg: ImageView =itemView.findViewById(R.id.product_img)
        val productNameTxt: TextView =itemView.findViewById(R.id.product_name_txt)
        val productPriceTxt: TextView =itemView.findViewById(R.id.price_txt)
        val productCard: ConstraintLayout =itemView.findViewById(R.id.product_card)
    }


}