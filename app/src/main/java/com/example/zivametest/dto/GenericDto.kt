package com.example.zivametest.dto

import com.google.gson.annotations.SerializedName

data class GenericDto<T>(
    @SerializedName("products") val result: MutableList<T>
)