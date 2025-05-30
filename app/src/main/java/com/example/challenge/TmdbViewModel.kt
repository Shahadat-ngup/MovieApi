package com.example.challenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class TmdbViewModel : ViewModel() {

    var movies by mutableStateOf<List<TmdbItem>>(emptyList())
    var tvShows by mutableStateOf<List<TmdbItem>>(emptyList())

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    private val apiKey = "0493b63522c42ebe37aa5c4b50c87eba"

    init {
        fetchTopRatedMovies()
        fetchTopRatedTv()
    }

    private fun fetchTopRatedMovies() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val response = RetrofitInstance.api.getTopRatedMovies(apiKey)
                movies = response.results
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Error fetching movies"
            }
            isLoading = false
        }
    }

    private fun fetchTopRatedTv() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val response = RetrofitInstance.api.getTopRatedTv(apiKey)
                tvShows = response.results
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Error fetching TV shows"
            }
            isLoading = false
        }
    }
}
