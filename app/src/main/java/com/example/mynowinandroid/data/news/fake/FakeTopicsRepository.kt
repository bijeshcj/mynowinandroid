package com.example.mynowinandroid.data.news.fake

import com.example.mynowinandroid.data.NiaPreferences
import com.example.mynowinandroid.data.news.Topic
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
        emit(networkJson.decodeFromString(FakeDataSource.topicsData))
    }.flowOn(dispatchers.IO)

    override suspend fun setFollowedTopicIds(followedTopicIds: Set<Int>) = niaPreferences.setFollowedTopicIds(followedTopicIds)

    override fun getFollowedTopicsIdsStream(): Flow<Set<Int>> = niaPreferences.followedTopicIds
}
