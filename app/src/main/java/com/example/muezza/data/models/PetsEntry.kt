package com.example.muezza.data.models

import com.example.muezza.data.remote.Clan
import com.example.muezza.data.remote.Comment
import com.example.muezza.data.remote.User

data class PetsEntry(
    val adoption: Boolean?,
    val age: Int?,
    val animal_type: String?,
    val category: String?,
    val clan: Clan?,
    val clan_uuid: String?,
    val comment: List<Comment>?,
    val content: String?,
    val created_at: String?,
    val image: String?,
    val slug: String?,
    val title: String?,
    val updated_at: String?,
    val user: User?,
    val user_uuid: String?,
    val uuid: String?
)
