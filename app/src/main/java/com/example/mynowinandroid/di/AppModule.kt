package com.example.mynowinandroid.di

import android.content.Context
import com.example.mynowinandroid.data.news.NewsRepository
import com.example.mynowinandroid.data.news.TopicsRepository
import com.example.mynowinandroid.data.news.fake.FakeNewsRepository
import com.example.mynowinandroid.data.news.fake.FakeTopicsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Binds
    fun bindsTopicRepository(fakeTopicsRepository: FakeTopicsRepository): TopicsRepository

    @Binds
    fun bindsNewsResourceRepository(fakeNewsRepository: FakeNewsRepository): NewsRepository

    @Binds
    fun bindsNiaDispatchers(defaultNiaDispatchers: DefaultNiaDispatchers): NiaDispatchers

    companion object {

//        @Provides
//        @Singleton
//        fun provideUserPreferencesDataStore(@ApplicationContext context: Context,
//        userPreference)

        @Provides
        @Singleton
        fun providesNetworkJson(): Json = Json {
            ignoreUnknownKeys = true
        }
    }
}
