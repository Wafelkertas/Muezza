package com.example.muezza.repository

import com.example.muezza.data.domain.UserDomain
import com.example.muezza.data.remote.User
import com.example.muezza.util.Resource

class UserRepository: IRepository <User, Resource<User>, UserDomain> {

    override suspend fun getListData(data: UserDomain): Resource<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getSingleData(data: UserDomain): User {
        TODO("Not yet implemented")
    }

    override suspend fun postData(data: UserDomain) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteData(data: UserDomain) {
        TODO("Not yet implemented")
    }

    override suspend fun updateData(data: UserDomain) {
        TODO("Not yet implemented")
    }


}