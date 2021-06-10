package com.example.jobtask

import android.app.Activity
import android.app.Application
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jobtask.CategoryAdapter.CategoryHolder

class CategoryAdapter(private val categoriesList:List<CategoryModel>,var application: FragmentActivity): RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.categories_item, parent, false) as View
        return CategoryHolder(view)
        }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
      val currentCategory=categoriesList[position]
       // holder.categoryImg
         holder.categoryNameTxt.setText(currentCategory.name)

        Glide.with(application).load(currentCategory.image_url).placeholder(R.drawable.bed_img).into(holder.categoryImg)
    }

    override fun getItemCount(): Int {
       return categoriesList.size
    }
    class CategoryHolder(view: View) : RecyclerView.ViewHolder(view) {
     val categoryImg:ImageView=view.findViewById(R.id.category_img)
     val categoryNameTxt:TextView=view.findViewById(R.id.category_name_txt)

    }

}