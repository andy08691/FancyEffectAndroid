package com.yian.fancyeffectandroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _pictures = MutableStateFlow<List<Int>>(emptyList())
    val pictures: StateFlow<List<Int>> get() = _pictures
    private val _bg = MutableStateFlow<Int>(R.drawable.broken_wall_bg2)
    val bg: StateFlow<Int> get() = _bg

    init {
        loadPictures()
    }

    private fun loadPictures() {
        viewModelScope.launch {
            _pictures.value = listOf(
                R.drawable.pikachu,
                R.drawable.eve,
                R.drawable.gengar,
                R.drawable.charizard,
                R.drawable.bulbasauar
            )
        }
    }

}