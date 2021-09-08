package com.example.zivametest.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zivametest.R
import com.example.zivametest.adapter.CartAdapter
import com.example.zivametest.model.Shop
import com.example.zivametest.util.Status
import com.example.zivametest.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*

class CartActivity : AppCompatActivity(){

    private lateinit var viewModel: CartViewModel
    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        setupUI()
        setupObservers()
    }

    private fun setupUI() {

        cartView.layoutManager = LinearLayoutManager(this@CartActivity)
        adapter = CartAdapter(arrayListOf())
        cartView.adapter = adapter

        checkout.setOnClickListener(){
            viewModel.deleteShop()
            val intent = Intent (this@CartActivity, CheckoutActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun setupObservers() {

        viewModel.getCart().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        cartView.visibility = View.VISIBLE
                        progress_bar1.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(resource.data as ArrayList<Shop>) }
                    }
                    Status.ERROR -> {
                        cartView.visibility = View.VISIBLE
                        progress_bar1.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        cartView.visibility = View.VISIBLE
                        progress_bar1.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private  fun retrieveList(users: ArrayList<Shop>) {

        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }


    }

}
