package com.example.muezza.repository

import android.util.Log
import com.example.muezza.data.Api
import com.example.muezza.data.dto.PetsDto
import com.example.muezza.data.remote.Data
import com.example.muezza.util.Resource
import java.lang.Exception
import javax.inject.Inject

class PetsRepository @Inject constructor(
    private val api : Api
) : IRepository<Resource<Data>, Resource<List<Data>>, PetsDto> {

    override suspend fun getListData(data: PetsDto): Resource<List<Data>> {
        val response = try {
            api.getPetsList(
                search = data.search,
                category = data.category,
                age = data.age,
                city_uuid = data.city_uuid,
                clan_uuid = data.clan_uuid,
                limit = data.limit
            )
        } catch (e: Exception) {
            return Resource.Error(message = "An unknown error occurred")
        }
        Log.d("getlist", "${response.data}")
        return Resource.Success(response.data)
    }

    override suspend fun getSingleData(data: PetsDto): Resource<Data> {
        val response = try {
            api.getSinglePet(slug = data.urlSlug)
        } catch (e:Exception) {
            return Resource.Error(message = "An unknown error occurred")
        }
        return Resource.Success(response)
    }

    override suspend fun postData(data: PetsDto) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteData(data: PetsDto) {
        TODO("Not yet implemented")
    }

    override suspend fun updateData(data: PetsDto) {
        TODO("Not yet implemented")
    }


}