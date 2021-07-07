package com.example.muezza.ui.screen.detailscreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muezza.data.models.PetsEntry
import com.example.muezza.data.remote.Data
import com.example.muezza.repository.Repository
import com.example.muezza.util.Resource
import com.example.muezza.util.dataToPetsEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewmodel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    // State for holding a single pet
    var singlePet : MutableState<PetsEntry?> = mutableStateOf(null)





    // Function to get a single pets
    fun getSinglePet(urlSlug:String) {
        viewModelScope.launch {
            val response = repository.getPets(urlArgument = urlSlug)
            when(response){
                is Resource.Success -> {
                    response.data?.let {data ->
                        val pet = dataToPetsEntry(data)
                        singlePet.value = pet
                    }
                }
                is Resource.Error -> {
                    Log.d("error", "${response.message}")
                }
            }
        }
    }
}