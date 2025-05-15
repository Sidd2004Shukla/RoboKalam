package com.example.robokalamassignment.data.api

import retrofit2.http.GET

data class Quote(
    val q: String,
    val a: String,
    val h: String
)

interface QuoteService {
    @GET("api/random")
    suspend fun getRandomQuote(): List<Quote>
}

object QuoteApi {
    private const val BASE_URL = "https://zenquotes.io/"
    
    fun create(): QuoteService {
        return retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(QuoteService::class.java)
    }
} 