package com.example.mynowinandroid.di

import com.example.mynowinandroid.data.local.NiADatabase
import com.example.mynowinandroid.data.local.dao.AuthorDao
import com.example.mynowinandroid.data.local.dao.EpisodeDao
import com.example.mynowinandroid.data.local.dao.NewsResourceDao
import com.example.mynowinandroid.data.local.dao.TopicDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DaosModule {

    companion object {
        @Provides
        fun providesAuthorDao(database: NiADatabase): AuthorDao = database.authorDao()

        @Provides
        fun providesTopicsDao(database: NiADatabase): TopicDao = database.topicDao()

        @Provides
        fun providesEpisodeDao(database: NiADatabase): EpisodeDao = database.episodeDao()

        @Provides
        fun providesNewsResourceDao(database: NiADatabase): NewsResourceDao =
            database.newsResourceDao()
    }
}
