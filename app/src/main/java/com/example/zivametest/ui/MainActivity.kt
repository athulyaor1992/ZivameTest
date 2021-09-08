package com.example.zivametest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelProviders.of
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zivametest.R
import com.example.zivametest.adapter.ShopAdapter
import com.example.zivametest.model.Shop
import com.example.zivametest.util.Status
import com.example.zivametest.viewmodel.ShopViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ShopViewModel
    private lateinit var adapter: ShopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ShopViewModel::class.java)

        setupUI()
        setupObservers()

    }

    private fun setupUI() {
        shopView.layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = ShopAdapter(arrayListOf())
        shopView.adapter = adapter
    }

    private fun setupObservers() {

        viewModel.getShop().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        shopView.visibility = View.VISIBLE
                        progress_bar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(resource.data!!.body()!!.result  as ArrayList<Shop>) }
                    }
                    Status.ERROR -> {
                        shopView.visibility = View.VISIBLE
                        progress_bar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        shopView.visibility = View.VISIBLE
                        progress_bar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
    private fun saveInDB(result: MutableList<Shop>) {
        viewModel.deleteShop();

        for (i in result) {
            viewModel.insertShop(i);

        }
    }

    private  fun retrieveList(users: ArrayList<Shop>) {

        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }


    }

}