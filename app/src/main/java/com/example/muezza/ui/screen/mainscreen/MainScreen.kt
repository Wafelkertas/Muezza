package com.example.muezza.ui.screen.mainscreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import coil.request.ImageRequest
import com.example.muezza.R
import com.example.muezza.data.remote.Data
import com.example.muezza.ui.screen.detailscreen.DetailScreenViewmodel
import com.example.muezza.util.imageUrlManipulation
import com.google.accompanist.coil.CoilImage


@Composable
fun PetsListScreen(
    navController: NavController,
    mainScreenViewmodel : MainScreenViewmodel = hiltNavGraphViewModel(),
    detailScreenViewmodel : DetailScreenViewmodel = hiltNavGraphViewModel()
){
    val dataList by remember { mainScreenViewmodel.dataList }
    mainScreenViewmodel.loadList()

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column{
            Log.d("viewmodel instance", detailScreenViewmodel.toString())
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(R.drawable.pawprint),
                contentDescription = "testing",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
                )
            SearchBar(
                hint = "Search....",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            PetList(
                navController = navController,
                entries = dataList,
                detailScreenViewmodel = detailScreenViewmodel,
                mainScreenViewmodel = mainScreenViewmodel
            )
        }
    }
}

@Composable
fun SearchBar(
    modifier : Modifier = Modifier,
    hint : String = "",
    onSearch : (String) -> Unit = {}
){
    var text by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier){
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it != FocusState.Active
                }

        )
        if (isHintDisplayed){
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun PetList(
    navController: NavController,
    entries: List<Data>,
    mainScreenViewmodel: MainScreenViewmodel,
    detailScreenViewmodel: DetailScreenViewmodel
){
    val endReached by remember {  mainScreenViewmodel.endReached }
    val totalData by remember { mainScreenViewmodel.totalData    }
//    val currentPage by remember { mainScreenViewmodel.currentPage    }

    if (totalData >= entries.count()  && !endReached){
        Log.d("countandpetlist", "${entries.count()} and ${totalData} and $endReached")
        mainScreenViewmodel.loadList()
    }

    LazyColumn(contentPadding = PaddingValues(16.dp)) {


        items(entries){entry ->
            PetEntry(
                entry = entry,
                navController = navController,
                detailScreenViewmodel = detailScreenViewmodel
            )
        }
    }
}

@Composable
fun PetEntry(
    entry: Data,
    navController: NavController,
    detailScreenViewmodel: DetailScreenViewmodel
){

    val newImgUrl = imageUrlManipulation(entry.image)
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable {
                detailScreenViewmodel.getSinglePet(imageUrlManipulation(entry.slug))
                navController.navigate(
                    "detail_screen/${entry.slug}"
                )
                Log.d("viewmodel instance", detailScreenViewmodel.toString())
            }
    ) {
        if (entry == null) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        } else {
            CoilImage(
                request = ImageRequest.Builder(LocalContext.current)
                    .data(newImgUrl)
                    .build(),
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterVertically),
                contentDescription = entry.title
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = entry.title)
        }
    }
}