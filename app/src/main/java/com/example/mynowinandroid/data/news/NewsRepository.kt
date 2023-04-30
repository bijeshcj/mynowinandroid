package com.example.mynowinandroid.data.news

import com.example.mynowinandroid.data.model.NewsResource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    /**
     * Returns available news resources as a stream.
     */
    fun getNewsResourcesStream(): Flow<List<NewsResource>>

    /**
     * Returns available news resource as a stream filtered by the topic
     */
    fun getNewsResourcesStream(filterTopicsIds: Set<Int>): Flow<List<NewsResource>>

}