package com.example.mynowinandroid.data.fake

import com.example.mynowinandroid.data.NiaPreferences
import com.example.mynowinandroid.data.model.Topic
import com.example.mynowinandroid.data.network.NetworkTopic
import com.example.mynowinandroid.data.news.TopicsRepository
import com.example.mynowinandroid.di.NiaDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class FakeTopicsRepository @Inject constructor(
    private val dispatchers: NiaDispatchers,
    private val networkJson: Json,
    private val niaPreferences: NiaPreferences
) : TopicsRepository {
    override fun getTopicsStream(): Flow<List<Topic>> = flow<List<Topic>> {
        emit(networkJson.decodeFromString<List<NetworkTopic>>(FakeDataSource.topicsData).map {
            Topic(
                id = it.id,
                name = it.name,
                description = it.description
            )
        })
    }.flowOn(dispatchers.IO)

    override suspend fun setFollowedTopicIds(followedTopicIds: Set<Int>) = niaPreferences.setFollowedTopicIds(followedTopicIds)

    override fun getFollowedTopicsIdsStream(): Flow<Set<Int>> = niaPreferences.followedTopicIds
}
