package com.aayar94.aquatracker_data.remote

import com.aayar94.aquatracker_domain.model.Articles
import retrofit2.http.GET

interface ArticlesApi {

    @GET(".json")
    suspend fun getArticles(): Articles

    companion object{
        const val BASE_URL="https://aquatick94-default-rtdb.europe-west1.firebasedatabase.app/"
    }
}