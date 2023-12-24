package com.example.kmtest.ui.third

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kmtest.databinding.ActivityMainBinding
import com.example.kmtest.ui.LoadingStateAdapter
import com.example.kmtest.ui.UserAdapter
import com.example.kmtest.ui.UserViewModel
import com.example.kmtest.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private val userVieModel by viewModels<UserViewModel> { ViewModelFactory.getInstance(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.icBack.setOnClickListener {
            onBackPressed()
        }

        setupAdapter()
        setupData()
        setupAction()
    }


    private fun setupData() {
        userVieModel.getListUser.observe(this@MainActivity) { pagingData ->
            userAdapter.submitData(lifecycle, pagingData)
        }
    }

    private fun setupAdapter() {
        userAdapter = UserAdapter()
        activityMainBinding.rvUser.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter.withLoadStateFooter(
                footer =  LoadingStateAdapter { userAdapter.retry() }
            )

        }
    }

    private fun setupAction() {
        activityMainBinding.swipeRefresh.apply {
            userAdapter.refresh()
            isRefreshing = false
        }
    }
}