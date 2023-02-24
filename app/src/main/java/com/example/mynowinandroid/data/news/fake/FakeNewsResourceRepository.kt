package com.example.mynowinandroid.data.news.fake

import android.content.Context
import com.example.mynowinandroid.R
import com.example.mynowinandroid.data.news.NewsResource
import com.example.mynowinandroid.data.news.NewsResourceRepository
import kotlinx.coroutines.flow.Flow

class FakeNewsResourceRepository(private val context:Context) : NewsResourceRepository {

    override fun monitor(): Flow<List<NewsResource>> {
        context.resources.openRawResource(R.raw.data)
        TODO("Deserialize json and return news resources")
    }
}