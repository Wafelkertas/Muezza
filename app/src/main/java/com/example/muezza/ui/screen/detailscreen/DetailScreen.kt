package com.example.muezza.ui.screen.detailscreen


import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import coil.request.ImageRequest
import com.example.muezza.data.models.PetsEntry
import com.example.muezza.util.imageUrlManipulation
import com.google.accompanist.coil.CoilImage
import kotlinx.coroutines.launch


@Composable
fun DetailScreen(
    slugArgument:String,
    viewModel: DetailScreenViewmodel = hiltNavGraphViewModel()
    ) {
    val data by remember {viewModel.singlePet}

    viewModel.getSinglePet(slugArgument)


    val newImgUrl = data?.image?.let { imageUrlManipulation(it) }

    if (data == null) Text(text = "loading")
    else data?.let { petEntry ->
        CoilImage(
            request = ImageRequest.Builder(LocalContext.current)
                .data(newImgUrl)
                .build(),
            modifier= Modifier
                .size(120.dp),
            contentDescription = petEntry.title
        )
    }



}

