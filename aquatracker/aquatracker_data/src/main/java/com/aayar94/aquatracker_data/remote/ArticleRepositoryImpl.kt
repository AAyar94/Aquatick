package com.aayar94.aquatracker_data.remote

import com.aayar94.aquatracker_domain.model.Articles
import com.aayar94.aquatracker_domain.repository.ArticleRepository
import com.google.firebase.Firebase
import com.google.firebase.database.database

class ArticleRepositoryImpl : ArticleRepository {

    val database = Firebase.database.reference
    override fun getArticles(): Articles {
        TODO("Not yet implemented")
    }

}