package com.backdoor.walcartandroidtest.View.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo3.ApolloClient
import com.backdoor.walcartandroidtest.Model.CategoryRepository
import com.backdoor.walcartandroidtest.Model.ApiClient
import com.backdoor.walcartandroidtest.R
import com.backdoor.walcartandroidtest.View.Model.CategoryFragment.CategoryRootCatRecyclerAdapter
import com.backdoor.walcartandroidtest.View.Model.CategoryFragment.CategorySubCatRecyclerAdapter
import com.backdoor.walcartandroidtest.databinding.FragmentCategoryBinding
import com.backdoor.walcartandroidtest.viewModel.CategoryViewModel
import com.backdoor.walcartandroidtest.viewModel.CategoryViewModelFactory
import com.example.GetCategoriesListQuery

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private var rootCatRecyclerView: RecyclerView? = null
    private var recyclerViewSubCat: RecyclerView? = null
    private var rootCatAdapter: CategoryRootCatRecyclerAdapter? = null
    private var subCatAdapter: CategorySubCatRecyclerAdapter? = null
    private val rootCatDataList: List<GetCategoriesListQuery.Category>? = null
    private val subCatDataList: List<GetCategoriesListQuery.Parent1?>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)
        val v = binding.root

        val apolloClient = ApolloClient.Builder().serverUrl(ApiClient.BASE_URL).build()
        val catRepository = CategoryRepository(apolloClient)

        val viewModel: CategoryViewModel = ViewModelProvider(
            this, CategoryViewModelFactory(activity, catRepository)
        )[CategoryViewModel::class.java]

        binding.catViewModel = viewModel
        binding.lifecycleOwner = this

        recyclerViewInit()

        viewModel.getAllCategories()

        viewModel.categoriesList.observe(viewLifecycleOwner) {
            Log.d("ResultCat", "Result: $it");
            val adapter = it?.let { it1 ->
                CategoryRootCatRecyclerAdapter(requireContext(),
                    it1,
                    object : CategoryRootCatRecyclerAdapter.OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            val subCatAdapter = it1[position].parents?.let { it2 ->
                                CategorySubCatRecyclerAdapter(
                                    requireContext(), it2
                                )
                            }
                            recyclerViewSubCat!!.adapter = subCatAdapter
                        }
                    })
            }
            rootCatRecyclerView!!.adapter = adapter
        }

        return v
    }

    private fun recyclerViewInit() {

        rootCatRecyclerView = binding.recyclerView
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(activity, 1)
        rootCatRecyclerView!!.layoutManager = layoutManager
        rootCatAdapter = CategoryRootCatRecyclerAdapter(requireContext(),
            rootCatDataList,
            object : CategoryRootCatRecyclerAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {}
            })
        rootCatRecyclerView!!.adapter = rootCatAdapter

        recyclerViewSubCat = binding.recyclerViewSubCat
        val layoutManager1: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        recyclerViewSubCat!!.layoutManager = layoutManager1
        subCatAdapter = subCatDataList?.let { CategorySubCatRecyclerAdapter(requireContext(), it) }
        recyclerViewSubCat!!.adapter = subCatAdapter

    }

}