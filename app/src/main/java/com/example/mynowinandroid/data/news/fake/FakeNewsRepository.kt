package com.example.mynowinandroid.data.news.fake

import com.example.mynowinandroid.data.news.NewsRepository
import com.example.mynowinandroid.data.news.NewsResource
import com.example.mynowinandroid.di.NiaDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
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
    override fun getNewsResourcesStream(): Flow<List<NewsResource>> = flow {
        emit(networkJson.decodeFromString<ResourceData>(FakeDataSource.data).resources)
    }.flowOn(dispatchers.IO)

    override fun getNewsResourcesStream(filterTopicsIds: Set<Int>): Flow<List<NewsResource>> =
        getNewsResourcesStream().map { newsResources ->
            newsResources.filter { it.topics.intersect(filterTopicsIds.toSet()).isNotEmpty() }
        }
}

@kotlinx.serialization.Serializable
private data class ResourceData(
    val resources: List<NewsResource>,
)
