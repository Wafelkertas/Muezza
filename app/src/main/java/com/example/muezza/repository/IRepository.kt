package com.example.muezza.repository

/*
 Type T is for Resource<Domain>, a Domain wrapped by a Resource Object
 Type U is for Resource<Domain>, a Domain wrapped by a Resource Object
 Type S is for Data Transfer Object
*/
interface IRepository <T, U, S> {
    suspend fun getListData(data:S) : U
    suspend fun getSingleData(data:S) : T
    suspend fun postData(data: S)
    suspend fun deleteData(data: S)
    suspend fun updateData(data: S)
}