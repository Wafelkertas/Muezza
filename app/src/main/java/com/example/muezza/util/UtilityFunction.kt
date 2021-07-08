package com.example.muezza.util

import androidx.recyclerview.widget.DiffUtil
import com.example.muezza.data.models.PetsEntry
import com.example.muezza.data.remote.City
import com.example.muezza.data.remote.Data

// Function to convert Model into Entity
fun dataToPetsEntry(data:Data): PetsEntry{
    return(
            PetsEntry(
                adoption = data.adoption,
                age = data.age,
                category = data.category,
                clan = data.clan,
                image = data.image,
                slug = data.slug,
                title = data.title,
                uuid = data.uuid,
                city = data.city,
                province = data.province
            )
            )
}

// Function for manipulate HTTP url into HTTPS
fun imageUrlManipulation(imageUrl: String): String {
    val imgUrl = imageUrl.drop(4)
    return "https${imgUrl}"
}



//fun listComparator(firstList: List<Data>, secondList: List<Data>) : MutableList<Data>{
//    var newList: MutableList<Data> = mutableListOf()
//
//    val difference : MutableList<Data> = secondList.filterNot { firstList.contains(it.uuid) }.toMutableList()
//    for(i in difference){
//        println(i)
//        newList = (firstList + difference) as MutableList<Data>
//    }
//    return newList
//}