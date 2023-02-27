package com.example.mynowinandroid.data.news.fake


import com.example.mynowinandroid.data.news.NewsResource
import com.example.mynowinandroid.data.news.NewsResourceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class FakeNewsResourceRepository(private val ioDispatcher:CoroutineDispatcher) : NewsResourceRepository {
    private val deserializer = Json{ignoreUnknownKeys = true}

    override fun monitor(): Flow<List<NewsResource>> = flow {
        emit(deserializer.decodeFromString<ResourceData>(FakeDataSource.data).resources)
    }.flowOn(ioDispatcher)
}

@kotlinx.serialization.Serializable
private data class ResourceData(
    val resources: List<NewsResource>
)