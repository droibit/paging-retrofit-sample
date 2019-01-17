package com.star_zero.pagingretrofitsample.ui

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.star_zero.pagingretrofitsample.R
import com.star_zero.pagingretrofitsample.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = RepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.recycler.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        binding.recycler.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                this, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )
        binding.recycler.adapter = adapter

        viewModel.repos.observe(this, Observer { pagedList ->
            Timber.d("Receive Result")
            adapter.submitList(pagedList)
        })

        viewModel.networkState.observe(this, Observer { networkState ->
            Timber.d("NetworkState: $networkState")
        })
    }
}
