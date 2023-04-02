package com.example.mynowinandroid.data.news.fake

import com.example.mynowinandroid.data.NiaPreferences
import com.example.mynowinandroid.data.news.Topic
import com.example.mynowinandroid.data.news.TopicsRepository
import com.example.mynowinandroid.di.NiaDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import javax.inject.Inject

class FakeTopicsRepository @Inject constructor(
    private val dispatchers: NiaDispatchers,
    private val networkJson: Json
//    private val niaPreferences: NiaPreferences
) : TopicsRepository {
    override fun getTopicsStream(): Flow<List<Topic>> {
        TODO("Not yet implemented")
    }

    override suspend fun setFollowedTopicIds(followedTopicIds: Set<Int>) {
        TODO("Not yet implemented")
    }

    override fun getFollowedTopicsIdsStream(): Flow<Set<Int>> {
        TODO("Not yet implemented")
    }
}