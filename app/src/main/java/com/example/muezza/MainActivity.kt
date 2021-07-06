package com.example.muezza

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.muezza.ui.screen.detailscreen.DetailScreen

import com.example.muezza.ui.screen.mainscreen.PetsListScreen
import com.example.muezza.ui.ui.MuezzaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuezzaTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "list_screen") {
                    composable("list_screen"){
                        PetsListScreen(navController = navController)
                    }
                    composable(
                        "detail_screen/{slug}",
                        arguments = listOf(
                            navArgument("slug"){
                                type = NavType.StringType
                            }
                        )){
                            val url = remember {
                                it.arguments?.getString("slug")
                            }
                        if (url != null) {
                            DetailScreen(slugArgument = url)
                        } else{
                            Log.d("Data Slug error", "error slug")
                        }

                    }
                }
            }
        }
    }
}

