package com.backdoor.walcartandroidtest.View.Model.CategoryFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.backdoor.walcartandroidtest.R;

import java.util.List;


public class CategoryRootCatRecyclerAdapter extends RecyclerView.Adapter<CategoryRootCatRecyclerAdapter.MyViewHolder> {

    private List<CategoryRootCat_Data> contact;
    private Context mContext;
    private int catSelectedPosition = -1;

    public CategoryRootCatRecyclerAdapter(List<CategoryRootCat_Data> contacts, Context context) {
        this.contact = contacts;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_root_cat, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        try {
            if (position == 0) {
                holder.view1.setVisibility(View.GONE);
            }
            if (position == getItemCount()) {
                holder.view2.setVisibility(View.GONE);
            }

            holder.parentLayout.setOnClickListener(view -> {
                Log.d("CategoryRootCat", "cat clicked");
                checkSelected();
                catSelectedPosition = holder.getAdapterPosition();
                holder.img.setImageTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.catSelectedColor)));
                holder.tittle.setTextColor(mContext.getResources().getColor(R.color.catSelectedColor));
                holder.view1.setBackgroundColor(mContext.getResources().getColor(R.color.catSelectedColor));
                holder.view2.setBackgroundColor(mContext.getResources().getColor(R.color.catSelectedColor));

            });

            itemUpdateChangeBG(holder, position);

        } catch (Exception ex) {
            Log.d("CategoryRootCat", "error: " + ex.getMessage());
        }

    }

    private void itemUpdateChangeBG(MyViewHolder holder, int position) {
        if (position == catSelectedPosition) {
            holder.img.setImageTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.catSelectedColor)));
            holder.tittle.setTextColor(mContext.getResources().getColor(R.color.catSelectedColor));
            holder.view1.setBackgroundColor(mContext.getResources().getColor(R.color.catSelectedColor));
            holder.view2.setBackgroundColor(mContext.getResources().getColor(R.color.catSelectedColor));
        } else {
            holder.img.setImageTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.navNormalColor)));
            holder.tittle.setTextColor(mContext.getResources().getColor(R.color.secondaryTextColor));
            holder.view1.setBackgroundColor(mContext.getResources().getColor(R.color.secondaryTextColor));
            holder.view2.setBackgroundColor(mContext.getResources().getColor(R.color.secondaryTextColor));
        }
    }

    private void checkSelected() {
        if (!(catSelectedPosition < 0)) {
            notifyItemChanged(catSelectedPosition);
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tittle;
        ImageView img;
        LinearLayout parentLayout;
        View view1, view2;

        MyViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.item_category_root_cat_layout);
            img = itemView.findViewById(R.id.item_category_root_cat_img);
            tittle = itemView.findViewById(R.id.item_category_root_cat_text);
            view1 = itemView.findViewById(R.id.item_category_root_cat_view1);
            view2 = itemView.findViewById(R.id.item_category_root_cat_view2);
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    public void notifyChangeData(List<CategoryRootCat_Data> dataList) {
        contact = dataList;
        notifyDataSetChanged();
    }

}
