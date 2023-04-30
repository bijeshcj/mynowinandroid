package com.example.mynowinandroid.data.fake

import com.example.mynowinandroid.data.model.NewsResource
import com.example.mynowinandroid.data.news.NewsRepository
import com.example.mynowinandroid.di.NiaDispatchers
import kotlinx.coroutines.flow.*
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 * [NewsRepository] implementation that provides static news resources to aid development
 */
class FakeNewsRepository @Inject constructor(
    private val dispatchers: NiaDispatchers,
    private val networkJson: Json,
) : NewsRepository {
    private val deserializer = Json { ignoreUnknownKeys = true }
    override fun getNewsResourcesStream(): Flow<List<NewsResource>> = flowOf(emptyList())

    override fun getNewsResourcesStream(filterTopicsIds: Set<Int>): Flow<List<NewsResource>> =
        flowOf(emptyList())
}
