package com.example.movieapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.db.MainDb
import com.example.movieapp.utils.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainDb: MainDb
): ViewModel(){
    val mainList = mutableStateOf(emptyList<ListItem>())

    private var job: Job? = null

    fun getAllItemsByCategory(cat: String){
        job?.cancel()
        job = viewModelScope.launch {
            mainDb.dao.getAllItemsByCategory(cat).collect{ list ->
                mainList.value = list
            }
        }
    }

    fun getFavorites(){
        job?.cancel()
        job = viewModelScope.launch {
            mainDb.dao.getFavorites().collect{ list ->
                mainList.value = list
            }
        }
    }

    fun insertItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.insertItem(item)
    }
    fun deleteItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.deleteItem(item)
    }
}