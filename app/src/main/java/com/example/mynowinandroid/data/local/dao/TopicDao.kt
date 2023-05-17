package com.example.mynowinandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mynowinandroid.data.local.entities.TopicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopicDao {
    @Query(value = "SELECT * FROM topics")
    fun getTopicEntitiesStream(): Flow<List<TopicEntity>>

    @Query(
        value =
        """
        SELECT * FROM topics WHERE id IN(:ids)
    """,
    )
    fun getTopicEntitiesStream(ids: Set<Int>): Flow<List<TopicEntity>>

    @Insert
    suspend fun saveTopics(entities: List<TopicEntity>)
}
