package com.example.mynowinandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mynowinandroid.data.local.entities.AuthorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthorDao {
    @Query(value = "SELECT * FROM authors")
    fun getAuthorsStream(): Flow<List<AuthorEntity>>

    @Insert
    suspend fun saveAuthorEntities(entities: List<AuthorEntity>)
}
