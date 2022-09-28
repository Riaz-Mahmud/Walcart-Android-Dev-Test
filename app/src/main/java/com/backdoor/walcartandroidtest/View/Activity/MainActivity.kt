package com.backdoor.walcartandroidtest.View.Activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Dao
import com.backdoor.walcartandroidtest.Model.RoomDB.CategoryDao
import com.backdoor.walcartandroidtest.Model.RoomDB.CategoryDataEntity
import com.backdoor.walcartandroidtest.Model.RoomDB.CategoryDatabase
import com.backdoor.walcartandroidtest.R
import com.backdoor.walcartandroidtest.View.Fragment.*
import com.backdoor.walcartandroidtest.databinding.ActivityMainBinding
import com.backdoor.walcartandroidtest.viewModel.MainViewModel
import com.backdoor.walcartandroidtest.viewModel.MainViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var navigationView: BottomNavigationView? = null
    private var homeFragment: HomeFragment? = null
    private var id = 0

    var datalist: List<CategoryDataEntity>? = null

    var database: CategoryDao? = null

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(this)
        )[MainViewModel::class.java]
        binding.mainViewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        viewModel.checkNightMode()

        init()
    }

    private fun init() {

        database = CategoryDatabase.getDatabase(application).getDao()

        navigationView = binding.bottomNavigationView;
        homeFragment = HomeFragment()
        val categoryFragment = CategoryFragment()
        val cartFragment = CartFragment()
        val favouriteFragment = FavouriteFragment()
        val accountFragment = AccountFragment()


        navigationView!!.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener setOnNavigationItemSelectedListener@{ menuItem: MenuItem ->
            id = menuItem.itemId
            when (id) {
                R.id.nav_home -> {
                    setFragment(homeFragment!!)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_category -> {
                    setFragment(categoryFragment)
                    return@setOnNavigationItemSelectedListener true;
                }
                R.id.nav_cart -> {
                    setFragment(cartFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_favourite -> {
                    setFragment(favouriteFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_account -> {
                    setFragment(accountFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        })
        navigationView!!.selectedItemId = R.id.nav_home
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        if (id != R.id.nav_home) {
            navigationView!!.selectedItemId = R.id.nav_home
            setFragment(homeFragment!!)
        } else super.onBackPressed()
    }
}