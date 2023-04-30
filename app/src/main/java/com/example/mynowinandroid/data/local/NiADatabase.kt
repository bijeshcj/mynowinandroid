package com.example.mynowinandroid.data.local

import androidx.room.Database

import androidx.room.TypeConverters
import com.example.mynowinandroid.data.local.entities.*
import com.example.mynowinandroid.data.local.utilities.InstantConverter
import com.example.mynowinandroid.data.local.utilities.NewsResourceTypeConverter

@Database(
    entities = [
        AuthorEntity::class,
        EpisodeAuthorCrossRef::class,
        EpisodeEntity::class,
        NewsResourceAuthorCrossRef::class,
        NewsResourceEntity::class,
        NewsResourceTopicCrossRef::class,
        TopicEntity::class,
    ],
    version = 1,
)
@TypeConverters(
    InstantConverter::class,
    NewsResourceTypeConverter::class
)
abstract class NiADatabase
