package com.example.zivametest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zivametest.R
import com.example.zivametest.model.Shop
import kotlinx.android.synthetic.main.shop_item.view.*
import java.text.SimpleDateFormat


class ShopAdapter(
    private val users: ArrayList<Shop?>,

) : RecyclerView.Adapter<ShopAdapter.DataViewHolder>() {

    interface CellClickListener {
        fun onCellClickListener(data: Shop)
    }


    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: Shop) {
            itemView.apply {

                shopName.text = user.name
                shopPrice.text = "INR "+user.price+"/-"

                Glide.with(this)
                    .load(user.image_url)
                    .circleCrop()
                    .into(shopImage);

/*                val matchScore = user.Score
                teamAscore.text = matchScore.substring(0, 1)
                teamBscore.text = matchScore.substring(
                    matchScore.length - 1,
                    matchScore.length)

                Glide.with(this)
                    .load(user.link_A)
                    .circleCrop()
                    .into(teamAimage);

                Glide.with(this)
                    .load(user.link_B)
                    .circleCrop()
                    .into(teamBimage);

                val teamAName = user.Team_A
                val teamBName = user.Team_B

                teamAname.text = teamAName.substring(0, 3)
                teamBname.text = teamBName.substring(0, 3)

                var date = user.Date
                var spf = SimpleDateFormat("dd MMMM yyyy HH:mm")
                val newDate = spf.parse(date)
                spf = SimpleDateFormat("EEE dd MMM")
                date = spf.format(newDate)
                matchDate.text = date

                var time = user.Date
                var spf1 = SimpleDateFormat("dd MMMM yyyy HH:mm")
                val newTime = spf1.parse(time)
                spf1 = SimpleDateFormat("hh:mm a")
                time = spf1.format(newTime)
                matchTime.text = time*/

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

       /* holder.itemView.setOnClickListener{
            cellClickListener.onCellClickListener(data!!)
        }*/

    }

    fun addUsers(users: List<Shop>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }

}