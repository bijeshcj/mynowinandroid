package com.example.mynowinandroid.testutil

import com.example.mynowinandroid.data.model.NewsResource
import com.example.mynowinandroid.data.news.NewsRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

class TestNewsRepository : NewsRepository {

    private val newsResourcesFlow: MutableSharedFlow<List<NewsResource>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    override fun getNewsResourcesStream(): Flow<List<NewsResource>> = newsResourcesFlow

    override fun getNewsResourcesStream(filterTopicsIds: Set<Int>): Flow<List<NewsResource>> = getNewsResourcesStream()
        .map { newsResources ->
            newsResources.filter {
                it.topics.intersect(filterTopicsIds).isNotEmpty()
            }
        }

    fun sendNewsResources(newsResources: List<NewsResource>) {
        newsResourcesFlow.tryEmit(newsResources)
    }
}
