package com.example.zivametest.network



import com.example.zivametest.dto.GenericDto
import com.example.zivametest.model.Shop
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("nancymadan/assignment/db")
    suspend fun getShop(): Response<GenericDto<Shop?>>



}