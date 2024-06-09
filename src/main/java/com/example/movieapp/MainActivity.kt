package com.example.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.components.InfoScreen
import com.example.movieapp.components.MainScreen
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.utils.ListItem
import com.example.movieapp.utils.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var item: ListItem? = null
            MovieAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Routes.MAIN_SCREEN)
                {
                    composable(Routes.MAIN_SCREEN){
                        MainScreen{listItem ->
                            item = listItem
                            navController.navigate(Routes.INFO_SCREEN)
                        }
                    }
                    composable(Routes.INFO_SCREEN){
                        InfoScreen(item = item!!)
                    }
                }
            }
        }
    }
}


