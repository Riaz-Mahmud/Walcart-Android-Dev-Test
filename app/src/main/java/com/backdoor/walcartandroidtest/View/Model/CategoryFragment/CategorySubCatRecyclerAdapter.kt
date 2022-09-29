package com.backdoor.walcartandroidtest.View.Model.CategoryFragment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.backdoor.walcartandroidtest.R
import com.backdoor.walcartandroidtest.databinding.ItemCategorySubCatBinding
import com.example.GetCategoriesListQuery

class CategorySubCatRecyclerAdapter(
    private val mContext: Context,
    private var contact: List<GetCategoriesListQuery.Parent1?>
) :
    RecyclerView.Adapter<CategorySubCatRecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCategorySubCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        try {
            Log.d("CategoryRootCat", "SubCat: $contact")

            holder.binding.itemCategorySubCatTitle.text = contact[position]?.enName ?: ""

            holder.binding.itemCategorySubCatLayout.setOnClickListener {
                if (holder.binding.itemCategorySubCatDetails.visibility == View.GONE){
                    holder.binding.itemCategorySubCatDetails.visibility = View.VISIBLE
                    holder.binding.itemCategorySubCatTitleIcon.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                    holder.binding.itemCategorySubCatTitleIcon.setColorFilter(ContextCompat.getColor(mContext,R.color.catSelectedColor))
                    holder.binding.itemCategorySubCatTitle.setTextColor(mContext.resources.getColor(R.color.catSelectedColor))
                }else{
                    holder.binding.itemCategorySubCatDetails.visibility = View.GONE
                    holder.binding.itemCategorySubCatTitleIcon.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                    holder.binding.itemCategorySubCatTitleIcon.setColorFilter(ContextCompat.getColor(mContext,R.color.black_status_bar))
                    holder.binding.itemCategorySubCatTitle.setTextColor(mContext.resources.getColor(R.color.black_status_bar))
                }
            }

        } catch (ex: Exception) {
            Log.d("CategoryRootCat", "error: " + ex.message)
        }
    }

    override fun getItemCount(): Int {
        return if (contact.isEmpty()) {
            0
        } else {
            contact.size
        }
    }

    class MyViewHolder(val binding: ItemCategorySubCatBinding) : RecyclerView.ViewHolder(binding.root)

}
