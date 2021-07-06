package com.example.muezza.data.remote

data class Comment(
    val comment: String,
    val created_at: String,
    val deleted_at: Any,
    val user_uuid: String,
    val uuid: String
)