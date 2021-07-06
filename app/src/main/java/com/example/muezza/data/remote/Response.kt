package com.example.muezza.data.remote

data class Response(
    val currentPage: String,
    val `data`: List<Data>,
    val limit: String,
    val totalData: Int,
    val totalPage: Int
)