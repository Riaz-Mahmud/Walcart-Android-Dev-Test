package com.backdoor.walcartandroidtest.View.Model.CategoryFragment

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.backdoor.walcartandroidtest.R
import com.example.GetCategoriesListQuery

class CategorySubCatRecyclerAdapter(
    private val mContext: Context,
    private var contact: List<GetCategoriesListQuery.Parent1?>
) :
    RecyclerView.Adapter<CategorySubCatRecyclerAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_sub_cat, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        try {
            Log.d("CategoryRootCat", "SubCat: $contact")
            holder.tittle.text = contact[position]?.enName ?: ""

            holder.tittleIcon.setOnClickListener {
                if (holder.detailsLayout.visibility == View.GONE){
                    holder.detailsLayout.visibility = View.VISIBLE
                    holder.tittleIcon.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                    holder.tittleIcon.setColorFilter(ContextCompat.getColor(mContext,R.color.catSelectedColor))
                    holder.tittle.setTextColor(mContext.resources.getColor(R.color.catSelectedColor))
                }else{
                    holder.detailsLayout.visibility = View.GONE
                    holder.tittleIcon.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                    holder.tittleIcon.setColorFilter(ContextCompat.getColor(mContext,R.color.black_status_bar))
                    holder.tittle.setTextColor(mContext.resources.getColor(R.color.black_status_bar))
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

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var parentLayout: LinearLayout
        var detailsLayout: LinearLayout
        var tittle: TextView
        var tittleIcon: ImageView

        init {
            parentLayout = itemView.findViewById(R.id.item_category_sub_cat_layout)
            tittle = itemView.findViewById(R.id.item_category_sub_cat_title)
            detailsLayout = itemView.findViewById(R.id.item_category_sub_cat_details)
            tittleIcon = itemView.findViewById(R.id.item_category_sub_cat_title_icon)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun notifyChangeData(dataList: List<GetCategoriesListQuery.Parent1?>) {
        contact = dataList
        notifyDataSetChanged()
    }
}
