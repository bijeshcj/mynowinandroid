package com.example.mynowinandroid.data.news

import kotlinx.coroutines.flow.Flow

interface NewsResourceRepository {

    fun monitor(): Flow<List<NewsResource>>

}