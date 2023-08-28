package com.example.tweetknot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweetknot.screens.CategoryScreen
import com.example.tweetknot.screens.DetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

//navigation work in this function
@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category") {
        composable(route = "category") {
            CategoryScreen {
                navController.navigate("detail/${it}")
            }
        }

        composable(route = "detail/{category}", arguments = listOf(
            navArgument("category") {
                type = NavType.StringType
            }
        )) {
            val categoryName = it.arguments!!.getString("category")
            categoryName?.let { name ->
                DetailScreen(category = name, onBackPressed = {
                    navController.navigateUp()
                })
            }
        }
    }
}
