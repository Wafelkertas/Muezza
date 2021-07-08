package com.example.muezza.ui.screen.mainscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
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
    private var currentPage = 1



    // State variable hold list of pets
    var dataList = mutableStateOf<MutableList<Data>>(mutableListOf())
    // State for when data is loading
    var isLoading = mutableStateOf(false)
    // State for when page scroll until the end
    var endReached = mutableStateOf(false)


    var totalData = mutableStateOf(0)

    init {

//        loadList()
        Log.d("viewmodel", "${dataList.value}")
        viewModelScope.launch {
            delay(10000)
            Log.d("listsize", "${dataList.value.size} ${currentPage}")
        }
    }



    // Function to call data from api
     fun loadList(){
        viewModelScope.launch {
            isLoading.value = true
            val response = repository.getPetsList(
            "","","","","", API_LIMIT.toString(), currentPage.toString()
            )
//            Log.d("viewmodeldata", "${response.data}")
            when(response) {
                is Resource.Success -> {
                    totalData.value = response.data!!.totalData
                    endReached.value = currentPage * API_LIMIT >= response.data.totalData
                    Log.d("endreach", "${endReached.value}")


                    isLoading.value = false

                    currentPage++

                    dataList.value = listComparator(dataList.value, response.data.data)



                }
                is Resource.Error -> {
                    Log.d("error", "${response.message}")
                }
            }
        }
    }

    private fun searchList(){

    }

    private fun listComparator(oldList: MutableList<Data>, newList: List<Data>): MutableList<Data> {
        Log.d("kepanggil", "kepanggil")
        val difference : MutableList<Data> = newList.filterNot { oldList.contains(it.uuid) }.toMutableList()
        return (oldList + difference).toMutableList()

    }







}