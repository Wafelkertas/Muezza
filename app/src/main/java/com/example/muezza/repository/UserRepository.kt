package com.example.muezza.repository

import com.example.muezza.data.dto.UserDto
import com.example.muezza.data.remote.User
import com.example.muezza.util.Resource

class UserRepository: IRepository <User, Resource<User>, UserDto> {

    override suspend fun getListData(data: UserDto): Resource<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getSingleData(data: UserDto): User {
        TODO("Not yet implemented")
    }

    override suspend fun postData(data: UserDto) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteData(data: UserDto) {
        TODO("Not yet implemented")
    }

    override suspend fun updateData(data: UserDto) {
        TODO("Not yet implemented")
    }


}