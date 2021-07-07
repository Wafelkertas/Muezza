package com.example.muezza.repository

import android.util.Log
import com.example.muezza.data.Api
import com.example.muezza.data.models.PetsEntry
import com.example.muezza.data.remote.Data
import com.example.muezza.data.remote.Response
import com.example.muezza.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class Repository @Inject constructor(
    private val api : Api
) {
    suspend fun getPetsList(
        search: String, category: String, age : String, city_uuid: String, clan_uuid : String, limit : String, page : String
    ) : Resource<Response> {
        val response = try {
            api.getPetsList(search = search, category = category, age = age, city_uuid = city_uuid, clan_uuid = clan_uuid, limit = limit, page = page)
        } catch (e: Exception) {
            return Resource.Error(message = "An unknown error occurred : $e")
        }
        Log.d("getlist", "${response.data}")
        return Resource.Success(response)
    }


    suspend fun getPets(
        urlArgument : String
    ) : Resource<Data> {
        val response = try {
            api.getSinglePet(slug = urlArgument)
        } catch (e:Exception) {
            return Resource.Error(message = "An unknown error occurred : $e")
        }
        return Resource.Success(response)
    }
}