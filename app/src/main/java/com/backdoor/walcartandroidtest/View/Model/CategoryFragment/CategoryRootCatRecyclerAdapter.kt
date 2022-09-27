package com.backdoor.walcartandroidtest.View.Model.CategoryFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.backdoor.walcartandroidtest.R
import com.example.GetCategoriesListQuery
import com.squareup.picasso.Picasso

class CategoryRootCatRecyclerAdapter(
    private val mContext: Context,
    private var contact: List<GetCategoriesListQuery.Category>?
) : RecyclerView.Adapter<CategoryRootCatRecyclerAdapter.MyViewHolder>() {
    private var catSelectedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_root_cat, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        try {

            holder.tittle.text = contact?.get(position)?.enName ?: ""

            val imageUrl = contact?.get(position)?.image?.url

            if (imageUrl != null) {
                Picasso.get()
                    .load(imageUrl.toString())
                    .error(R.drawable.icons_no_image_50)
                    .into(holder.img)
            } else {
                holder.img.setImageResource(R.drawable.icons_no_image_50)
            }

            if (position == 0) {
                holder.view1.visibility = View.GONE
            }
            if (position == itemCount) {
                holder.view2.visibility = View.GONE
            }
            holder.parentLayout.setOnClickListener { view: View? ->
                Log.d("CategoryRootCat", "cat clicked")
                checkSelected()
                catSelectedPosition = holder.adapterPosition
                holder.img.imageTintList = ColorStateList.valueOf(
                    mContext.resources.getColor(R.color.catSelectedColor)
                )
                holder.tittle.setTextColor(
                    mContext.resources.getColor(R.color.catSelectedColor)
                )
                holder.view1.setBackgroundColor(
                    mContext.resources.getColor(R.color.catSelectedColor)
                )
                holder.view2.setBackgroundColor(
                    mContext.resources.getColor(R.color.catSelectedColor)
                )
            }
            itemUpdateChangeBG(holder, position)
        } catch (ex: Exception) {
            Log.d("CategoryRootCat", "error: " + ex.message)
        }
    }

    private fun itemUpdateChangeBG(holder: MyViewHolder, position: Int) {
        if (position == catSelectedPosition) {
            holder.img.imageTintList =
                ColorStateList.valueOf(mContext.resources.getColor(R.color.catSelectedColor))
            holder.tittle.setTextColor(mContext.resources.getColor(R.color.catSelectedColor))
            holder.view1.setBackgroundColor(mContext.resources.getColor(R.color.catSelectedColor))
            holder.view2.setBackgroundColor(mContext.resources.getColor(R.color.catSelectedColor))
        } else {
            holder.img.imageTintList =
                ColorStateList.valueOf(mContext.resources.getColor(R.color.navNormalColor))
            holder.tittle.setTextColor(mContext.resources.getColor(R.color.secondaryTextColor))
            holder.view1.setBackgroundColor(mContext.resources.getColor(R.color.secondaryTextColor))
            holder.view2.setBackgroundColor(mContext.resources.getColor(R.color.secondaryTextColor))
        }
    }

    private fun checkSelected() {
        if (catSelectedPosition >= 0) {
            notifyItemChanged(catSelectedPosition)
        }
    }

    override fun getItemCount(): Int {
        return if (contact == null) {
            0
        } else if (contact!!.isEmpty()) {
            0
        } else {
            contact!!.size
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tittle: TextView
        var img: ImageView
        var parentLayout: LinearLayout
        var view1: View
        var view2: View

        init {
            parentLayout = itemView.findViewById(R.id.item_category_root_cat_layout)
            img = itemView.findViewById(R.id.item_category_root_cat_img)
            tittle = itemView.findViewById(R.id.item_category_root_cat_text)
            view1 = itemView.findViewById(R.id.item_category_root_cat_view1)
            view2 = itemView.findViewById(R.id.item_category_root_cat_view2)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun notifyChangeData(dataList: List<GetCategoriesListQuery.Category>?) {
        contact = dataList
        notifyDataSetChanged()
    }
}
