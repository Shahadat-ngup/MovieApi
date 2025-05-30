package com.example.challenge

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Data models
data class TmdbResponse(
    val results: List<TmdbItem>
)

data class TmdbItem(
    val id: Int,
    val title: String?, // For movies
    val name: String?,  // For TV shows
    val overview: String,
    val poster_path: String?
)

// Retrofit API interface
interface TmdbApi {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): TmdbResponse

    @GET("tv/top_rated")
    suspend fun getTopRatedTv(@Query("api_key") apiKey: String): TmdbResponse
}

// Retrofit instance object
object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: TmdbApi by lazy {
        retrofit.create(TmdbApi::class.java)
    }
}
