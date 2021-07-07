package com.example.muezza.data.remote

data class Data(
    val adoption: Boolean,
    val age: Int,
    val category: String,
    val city: City,
    val clan: Clan,
    val image: String,
    val province: Province,
    val slug: String,
    val title: String,
    val uuid: String
)