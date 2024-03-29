package com.example.mynowinandroid.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant


@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "publish_date")
    val publishDate: Instant,
    @ColumnInfo(name = "alternate_video")
    val alternateVideo: String?,
    @ColumnInfo(name = "alternate_audio")
    val alternateAudio: String?
)
