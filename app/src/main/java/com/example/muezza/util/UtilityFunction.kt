package com.example.muezza.util

import com.example.muezza.data.models.PetsEntry
import com.example.muezza.data.remote.Data

// Function to convert Model into Entity
fun dataToPetsEntry(data:Data): PetsEntry{
    return(
            PetsEntry(
                adoption = data.adoption,
                age = data.age,
                animal_type = data.animal_type,
                category = data.category,
                clan = data.clan,
                clan_uuid = data.clan_uuid,
                comment = data.comment,
                content = data.content,
                created_at = data.created_at,
                image = data.image,
                slug = data.slug,
                title = data.title,
                updated_at = data.updated_at,
                user = data.user,
                user_uuid = data.user_uuid,
                uuid = data.uuid
            )
            )
}

// Function for manipulate HTTP url into HTTPS
fun imageUrlManipulation(imageUrl: String): String {
    val imgUrl = imageUrl.drop(4)
    return "https${imgUrl}"
}