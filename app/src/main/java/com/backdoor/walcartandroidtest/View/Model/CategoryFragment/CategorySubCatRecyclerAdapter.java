package com.backdoor.walcartandroidtest.View.Model.CategoryFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.backdoor.walcartandroidtest.R;
import com.example.GetCategoriesListQuery;

import java.util.List;


public class CategorySubCatRecyclerAdapter extends RecyclerView.Adapter<CategorySubCatRecyclerAdapter.MyViewHolder> {

    private List<GetCategoriesListQuery.Category> contact;
    private Context mContext;

    public CategorySubCatRecyclerAdapter(List<GetCategoriesListQuery.Category> contacts, Context context) {
        this.contact = contacts;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_sub_cat, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        try {


        } catch (Exception ex) {
            Log.d("CategoryRootCat", "error: " + ex.getMessage());
        }

    }


    @Override
    public int getItemCount() {
        return 10;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {



        MyViewHolder(View itemView) {
            super(itemView);

        }
    }


    @SuppressLint("NotifyDataSetChanged")
    public void notifyChangeData(List<GetCategoriesListQuery.Category> dataList) {
        contact = dataList;
        notifyDataSetChanged();
    }

}
