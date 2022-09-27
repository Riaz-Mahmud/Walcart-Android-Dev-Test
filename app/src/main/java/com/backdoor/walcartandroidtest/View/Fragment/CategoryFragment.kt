package com.backdoor.walcartandroidtest.View.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.backdoor.walcartandroidtest.R
import com.backdoor.walcartandroidtest.View.Model.CategoryFragment.*
import com.backdoor.walcartandroidtest.databinding.FragmentCategoryBinding
import com.backdoor.walcartandroidtest.viewModel.CategoryViewModel

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private var rootCatRecyclerView: RecyclerView? = null
    private var recyclerViewSubCat:RecyclerView? = null
    private var rootCatAdapter: CategoryRootCatRecyclerAdapter? = null
    private var subCatAdapter: CategorySubCatRecyclerAdapter? = null
    private val rootCatDataList: List<CategoryRootCat_Data>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)
        val v = binding.root
        val viewModel: CategoryViewModel = ViewModelProviders.of(this@CategoryFragment)[CategoryViewModel::class.java]
        viewModel.setActivity(activity)
        binding.catViewModel = viewModel
        binding.lifecycleOwner = this

        recyclerViewInit()

        return v;
    }

    private fun recyclerViewInit() {

        rootCatRecyclerView = binding.recyclerView
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(activity, 1)
        rootCatRecyclerView!!.layoutManager = layoutManager
        rootCatAdapter =
            CategoryRootCatRecyclerAdapter(
                rootCatDataList,
                requireContext()
            )
        rootCatRecyclerView!!.adapter = rootCatAdapter

        recyclerViewSubCat = binding.recyclerViewSubCat
        val layoutManager1: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        recyclerViewSubCat!!.layoutManager = layoutManager1
        subCatAdapter =
            CategorySubCatRecyclerAdapter(
                rootCatDataList,
                context
            );
        recyclerViewSubCat!!.adapter = subCatAdapter

    }

}