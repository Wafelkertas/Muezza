package com.example.muezza.ui.screen.mainscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muezza.data.remote.Data
import com.example.muezza.repository.PetsRepository
import com.example.muezza.repository.Repository
import com.example.muezza.repository.UserRepository
import com.example.muezza.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewmodel @Inject constructor(
    private val repository : Repository,
    private val petsRepository : PetsRepository
) : ViewModel(){

    // Variable that hold Api page
    private var currentPage = 0
    // Variable that hold Api data limit
    private var limit = 30

    // State variable hold list of pets
    var dataList = mutableStateOf<List<Data>>(listOf())
    // State for when data is loading
    var isLoading = mutableStateOf(false)
    // State for when page scroll until the end
    var endReached = mutableStateOf(false)

    init {
        loadList()
        Log.d("viewmodel", "${dataList.value}")
        viewModelScope.launch {
            delay(3000)
        }
    }



    // Function to call data from api
    private fun loadList(){
        viewModelScope.launch {
            isLoading.value = true
            val response = repository.getPetsList(
            "","","","","", ""
            )
            Log.d("viewmodeldata", "${response.data}")
            when(response) {
                is Resource.Success -> {
                    isLoading.value = false
                    dataList.value = response.data?.data ?: listOf()
                }
                is Resource.Error -> {
                    Log.d("error", "${response.message}")
                }
            }
        }
    }




}