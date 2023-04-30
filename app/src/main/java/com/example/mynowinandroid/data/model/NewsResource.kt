package com.example.mynowinandroid.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.mynowinandroid.data.local.entities.*

data class NewsResource(
    @Embedded
    val entity: NewsResourceEntity,
    @Relation(
        parentColumn = "episode_id",
        entityColumn = "id",
    )
    val episode: EpisodeEntity,
    @Relation(
        parentColumn = "news_resource_id",
        entityColumn = "author_id",
        associateBy = Junction(NewsResourceAuthorCrossRef::class),
    )
    val authors: List<AuthorEntity>,
    @Relation(
        parentColumn = "news_resource_id",
        entityColumn = "topic_id",
        associateBy = Junction(NewsResourceTopicCrossRef::class),
    )
    val topics: List<TopicEntity>,
)

// @kotlinx.serialization.Serializable
// data class NewsResource(
//    val episode: Int,
//    val title: String,
//    val content: String,
//    @SerialName("URL")
//    val url: String,
//    val authorName: String,
//    @kotlinx.serialization.Serializable(InstantSerializer::class)
//    val publishDate: Instant,
//    val type: String,
//    val topics: List<String>,
//    val alternateVideo: VideoInfo?,
// )
//
// @kotlinx.serialization.Serializable
// data class VideoInfo(
//    @SerialName("URL")
//    val url: String,
//    val startTimestamp: Int,
//    val endTimestamp: Int,
// )
//

