package com.example.zivametest.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.zivametest.R
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        Handler().postDelayed({

            progress_bar.visibility = View.GONE
            congratz.visibility = View.VISIBLE

        }, 3000)

        continueShop.setOnClickListener(){
            val intent = Intent (this@CheckoutActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}