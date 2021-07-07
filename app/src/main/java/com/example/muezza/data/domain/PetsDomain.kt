package com.example.muezza.data.domain

data class PetsDomain(
    val search : String = "",
    val category : String = "",
    val age : String = "",
    val city_uuid: String = "",
    val clan_uuid: String = "",
    val limit : String = "",
    val urlSlug : String = "",
    val page : String = ""
)
