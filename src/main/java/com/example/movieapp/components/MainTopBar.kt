package com.example.movieapp.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.MainViewModel
import com.example.movieapp.ui.theme.DarkBlue
import com.example.movieapp.ui.theme.MainBlue
import kotlinx.coroutines.launch
import com.example.movieapp.ui.theme.PaleBlue
import com.example.movieapp.utils.DrawerEvents

@Composable
fun MainTopBar(
    title: String,
    scaffoldState:ScaffoldState,
    onFavClick: () -> Unit
) {
    val coroutine = rememberCoroutineScope()
    TopAppBar(
        title = {
            Text(text = title,color = Color.White)
        },
        backgroundColor = MainBlue,
        navigationIcon = {
            IconButton(
                onClick = {
                    coroutine.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.Menu ,
                    contentDescription = "Menu",
                    tint = Color.White)
            }
        },
        actions = {
            IconButton(
                onClick = {

                    onFavClick()
                }
            ) {
                Icon(imageVector = Icons.Default.Favorite ,
                    contentDescription = "Favorite",
                    tint = Color.White)
            }
        }
    )

}