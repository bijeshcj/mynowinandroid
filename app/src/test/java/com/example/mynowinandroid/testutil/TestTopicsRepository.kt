package com.example.mynowinandroid.testutil

import com.example.mynowinandroid.data.model.Topic
import com.example.mynowinandroid.data.news.TopicsRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestTopicsRepository : TopicsRepository {

    private val _followedTopicIds: MutableSharedFlow<Set<Int>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    private val topicsFlow: MutableSharedFlow<List<Topic>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    override fun getTopicsStream(): Flow<List<Topic>> {
        return topicsFlow
    }

    override suspend fun setFollowedTopicIds(followedTopicIds: Set<Int>) {
        _followedTopicIds.tryEmit(followedTopicIds)
    }

    override fun getFollowedTopicsIdsStream(): Flow<Set<Int>> {
        return _followedTopicIds
    }

    fun sendTopics(topic: List<Topic>) {
        topicsFlow.tryEmit(topic)
    }

    fun getCurrentFollowedtopics(): Set<Int>? = _followedTopicIds.replayCache.firstOrNull()
}
