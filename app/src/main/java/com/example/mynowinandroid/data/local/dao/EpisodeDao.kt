package com.example.mynowinandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mynowinandroid.data.local.entities.EpisodeEntity
import com.example.mynowinandroid.data.model.Episode
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDao {
    @Query(value = "SELECT * FROM episodes")
    fun getEpisodeStream(): Flow<List<Episode>>

    @Insert
    suspend fun saveEpisodeEntities(entities: List<EpisodeEntity>)
}
