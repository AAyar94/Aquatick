package com.aayar94.aquatracker_domain.repository

import com.aayar94.aquatracker_domain.model.Articles

interface ArticleRepository {

    fun getArticles(): Articles

}