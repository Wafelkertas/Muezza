package com.example.muezza.data.remote

data class Data(
    val adoption: Boolean,
    val age: Int,
    val animal_type: String,
    val category: String,
    val clan: Clan,
    val clan_uuid: String,
    val comment: List<Comment>,
    val content: String,
    val created_at: String,
    val image: String,
    val slug: String,
    val title: String,
    val updated_at: String,
    val user: User,
    val user_uuid: String,
    val uuid: String
)