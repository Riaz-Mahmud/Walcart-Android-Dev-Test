package com.backdoor.walcartandroidtest.View.Model.CategoryFragment

import android.content.Context
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backdoor.walcartandroidtest.R
import com.backdoor.walcartandroidtest.databinding.ItemCategoryRootCatBinding
import com.example.GetCategoriesListQuery
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class CategoryRootCatRecyclerAdapter(
    private val mContext: Context,
    private var contact: List<GetCategoriesListQuery.Category>?,
    private val mListener: OnItemClickListener?
) : RecyclerView.Adapter<CategoryRootCatRecyclerAdapter.MyViewHolder>() {
    private var catSelectedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemCategoryRootCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        try {

            holder.binding.itemCategoryRootCatText.text = contact?.get(position)?.enName ?: ""

            val imageUrl = contact?.get(position)?.image?.url

            if (imageUrl != null) {
                Picasso.get()
                    .load(imageUrl.toString())
                    .error(R.drawable.icons_no_image_50)
                    .into(holder.binding.itemCategoryRootCatImg, object : Callback {
                        override fun onSuccess() {
                            holder.binding.itemCategoryRootCatProgressBar.visibility = View.GONE
                            holder.binding.itemCategoryRootCatImg.visibility = View.VISIBLE
                        }

                        override fun onError(ex: java.lang.Exception) {
                            holder.binding.itemCategoryRootCatProgressBar.visibility = View.GONE
                            holder.binding.itemCategoryRootCatImg.visibility = View.VISIBLE
                            holder.binding.itemCategoryRootCatImg.setImageResource(R.drawable.icons_no_image_50)
                        }
                    })
            } else {
                holder.binding.itemCategoryRootCatProgressBar.visibility = View.GONE
                holder.binding.itemCategoryRootCatImg.visibility = View.VISIBLE
                holder.binding.itemCategoryRootCatImg.setImageResource(R.drawable.icons_no_image_50)
            }

            if (position == 0) {
                holder.binding.itemCategoryRootCatView1.visibility = View.GONE
            }
            if (position == itemCount) {
                holder.binding.itemCategoryRootCatView2.visibility = View.GONE
            }
            holder.binding.itemCategoryRootCatLayout.setOnClickListener {
                Log.d("CategoryRootCat", "cat clicked")
                mListener?.onItemClick(position)
                checkSelected()
                catSelectedPosition = holder.adapterPosition
                holder.binding.itemCategoryRootCatImg.imageTintList = ColorStateList.valueOf(
                    mContext.resources.getColor(R.color.catSelectedColor)
                )
                holder.binding.itemCategoryRootCatText.setTextColor(
                    mContext.resources.getColor(R.color.catSelectedColor)
                )
                holder.binding.itemCategoryRootCatView1.setBackgroundColor(
                    mContext.resources.getColor(R.color.catSelectedColor)
                )
                holder.binding.itemCategoryRootCatView2.setBackgroundColor(
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
            holder.binding.itemCategoryRootCatImg.imageTintList =
                ColorStateList.valueOf(mContext.resources.getColor(R.color.catSelectedColor))
            holder.binding.itemCategoryRootCatText.setTextColor(mContext.resources.getColor(R.color.catSelectedColor))
            holder.binding.itemCategoryRootCatView1.setBackgroundColor(mContext.resources.getColor(R.color.catSelectedColor))
            holder.binding.itemCategoryRootCatView2.setBackgroundColor(mContext.resources.getColor(R.color.catSelectedColor))
        } else {
            holder.binding.itemCategoryRootCatImg.imageTintList =
                ColorStateList.valueOf(mContext.resources.getColor(R.color.navNormalColor))
            holder.binding.itemCategoryRootCatText.setTextColor(mContext.resources.getColor(R.color.secondaryTextColor))
            holder.binding.itemCategoryRootCatView1.setBackgroundColor(mContext.resources.getColor(R.color.secondaryTextColor))
            holder.binding.itemCategoryRootCatView2.setBackgroundColor(mContext.resources.getColor(R.color.secondaryTextColor))
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
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

    class MyViewHolder(val binding: ItemCategoryRootCatBinding) :
        RecyclerView.ViewHolder(binding.root)

}
