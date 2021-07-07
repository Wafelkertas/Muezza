package com.example.muezza.ui.screen.mainscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muezza.data.remote.Data
import com.example.muezza.repository.PetsRepository
import com.example.muezza.repository.Repository
import com.example.muezza.util.Constant.API_LIMIT
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

    // Variable that hold Api data limit
    private var limit = 30


    // State variable hold list of pets
    var dataList = mutableStateOf<MutableList<Data>>(mutableListOf())
    // State for when data is loading
    var isLoading = mutableStateOf(false)
    // State for when page scroll until the end
    var endReached = mutableStateOf(false)

    var currentPage = mutableStateOf(1)

    init {

        loadList()
        Log.d("viewmodel", "${dataList.value}")
        viewModelScope.launch {
            delay(10000)
            Log.d("list size", "${dataList.value.size} ${currentPage.value}")
        }
    }



    // Function to call data from api
     fun loadList(){
        viewModelScope.launch {
            isLoading.value = true
            val response = repository.getPetsList(
            "","","","","", API_LIMIT.toString(), currentPage.value.toString()
            )
            Log.d("viewmodeldata", "${response.data}")
            when(response) {
                is Resource.Success -> {
                    endReached.value = currentPage.value * API_LIMIT >= response.data!!.totalData
                    Log.d("end reach", "${endReached.value}")


                    isLoading.value = false

                    currentPage.value = currentPage.value + 1
                    listComparator(dataList.value, response.data.data)



                }
                is Resource.Error -> {
                    Log.d("error", "${response.message}")
                }
            }
        }
    }

    private fun searchList(){

    }

    private fun listComparator(oldList: List<Data>, newList: List<Data>) {


        val difference : MutableList<Data> = newList.filterNot { oldList.contains(it.uuid) }.toMutableList()

        for (i in difference){
            dataList.value.add(i)
        }


    }






}