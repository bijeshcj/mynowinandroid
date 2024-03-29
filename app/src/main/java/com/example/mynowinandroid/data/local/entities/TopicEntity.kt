package com.example.mynowinandroid.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "topics",
    indices = [
        Index(value = ["name"], unique = true),
    ],
)
data class TopicEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val followed: Boolean
)
