package com.example.tweetknot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweetknot.api.TweetAPI
import com.example.tweetknot.screens.CategoryScreen
import com.example.tweetknot.screens.DetailScreen
import com.example.tweetknot.ui.theme.TweetKnotTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
    NavHost(navController = navController, startDestination = "category" ){
        composable(route = "category"){
            CategoryScreen{
                navController.navigate("detail/${it}")
            }
        }

        composable(route = "detail/{category}", arguments = listOf(
            navArgument("category"){
                type = NavType.StringType
            }
        )){
            DetailScreen()
        }
    }
}
