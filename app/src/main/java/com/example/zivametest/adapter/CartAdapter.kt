package com.example.zivametest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zivametest.R
import com.example.zivametest.model.Shop
import kotlinx.android.synthetic.main.shop_item.view.*


class CartAdapter(
    private val users: ArrayList<Shop?>,

) : RecyclerView.Adapter<CartAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val addtocart: Button = itemView.findViewById(R.id.addtocart)
        val shopImage: ImageView = itemView.findViewById(R.id.shopImage)

        fun bind(user: Shop) {
            itemView.apply {

                addtocart.visibility = View.GONE

                shopName.text = user.name
                shopPrice.text = "INR "+user.price+"/-"

                Glide.with(this)
                    .load(user.image_url)
                    .circleCrop()
                    .into(shopImage)

                Glide.with(this)
                    .load(user.image_url)
                    .circleCrop()
                    .into(shopImageAnim)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.shop_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position]!!)

        val data = users[position]

       /* holder.addtocart.setOnClickListener {
            actionListener?.onItemTap(
                holder.shopImage,data
            )
        }*/


    }

    fun addUsers(users: List<Shop>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }

}