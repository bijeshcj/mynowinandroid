package com.example.mynowinandroid.data.news

import kotlinx.coroutines.flow.Flow

interface TopicsRepository {
    /**
     * Get the available topics as a stream
     */
    fun getTopicsStream(): Flow<List<Topic>>

    /**
     * Sets the users's currently followed topics
     */
    suspend fun setFollowedTopicIds(followedTopicIds: Set<Int>)

    /**
     * Returns the users currently followed topics
     */
    fun getFollowedTopicsIdsStream(): Flow<Set<Int>>
}
