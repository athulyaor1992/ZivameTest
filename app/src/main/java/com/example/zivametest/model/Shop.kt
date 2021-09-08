package com.example.zivametest.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Entity@Parcelize
data class Shop(

    @PrimaryKey(autoGenerate = true)
    @SerializedName("uid") val uid: Int ,

    val name: String,
    val price: String,
    val image_url: String,
    val rating: String

): Parcelable
