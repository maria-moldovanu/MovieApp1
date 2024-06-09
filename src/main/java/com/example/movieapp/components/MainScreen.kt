package com.example.movieapp.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.MainViewModel
import com.example.movieapp.ui.theme.MainBlue
import com.example.movieapp.utils.DrawerEvents
import com.example.movieapp.utils.ListItem
import kotlinx.coroutines.launch
import com.example.movieapp.ui.theme.Beige
import com.example.movieapp.ui.theme.LightBlue
import com.example.movieapp.ui.theme.PaleBlue

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    onClick: (ListItem) -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val mainList = mainViewModel.mainList

    val topBarTitle = remember{
        mutableStateOf("Comedy")
    }
    LaunchedEffect(Unit) {
        mainViewModel.getAllItemsByCategory(topBarTitle.value)
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MainTopBar(
                title = topBarTitle.value,
                scaffoldState
            ){
                topBarTitle.value = "Favorites"
                mainViewModel.getFavorites()
            }
        },
        drawerContent = {
            DrawerMenu(){event ->
                when(event){
                    is DrawerEvents.OnItemClick -> {
                        topBarTitle.value = event.title
                        mainViewModel.getAllItemsByCategory(event.title)
                    }
                }
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .background(color = LightBlue)){
            items(mainList.value){item ->
                MainListItem(item = item){listItem ->
                    onClick(listItem)
                }
            }
        }
    }
}
