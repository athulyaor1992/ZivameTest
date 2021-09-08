package com.example.zivametest.ui

import android.animation.Animator
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zivametest.R
import com.example.zivametest.adapter.ShopAdapter
import com.example.zivametest.model.Shop
import com.example.zivametest.util.CircleAnimationUtil
import com.example.zivametest.util.Status
import com.example.zivametest.viewmodel.ShopViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ShopViewModel
    private lateinit var adapter: ShopAdapter
    private  var itemCounter = 0

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


        adapter.setActionListener(object : ShopAdapter.ProductItemActionListener {

            override fun onItemTap(imageView: ImageView) {
                imageView?.let { makeFlyAnimation(it) }
            }
        })

    }


     private fun makeFlyAnimation(targetView: ImageView) {
        val destView = findViewById<View>(R.id.cartRelativeLayout) as RelativeLayout
        CircleAnimationUtil().attachActivity(this).setTargetView(targetView).setMoveDuration(1000)
            .setDestView(destView).setAnimationListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    addItemToCart()
                    Toast.makeText(this@MainActivity, "Continue Shopping...", Toast.LENGTH_SHORT).show()
                }

                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            }).startAnimation()
    }

    private fun addItemToCart() {
        val textView = findViewById<View>(R.id.textNotify) as TextView
        textView.text = (++itemCounter).toString()
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