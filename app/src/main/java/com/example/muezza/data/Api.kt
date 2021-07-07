package com.example.muezza.data

import com.example.muezza.data.remote.Data
import com.example.muezza.data.remote.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {

    @GET("posts?")
    suspend fun getPetsList(
        @Query("search") search : String,
        @Query("category") category : String,
        @Query("age") age : String,
        @Query("city_uuid") city_uuid : String,
        @Query("clan_uuid") clan_uuid : String,
        @Query("limit") limit : String,
        @Query("page") page : String
    ) : Response

    @GET("posts/{slug}")
    suspend fun getSinglePet(
        @Path("slug") slug:String
    ) : Data
}