package com.example.mynowinandroid.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.mynowinandroid.data.UserPreferencesSerializer
import com.example.mynowinandroid.data.fake.FakeNewsRepository
import com.example.mynowinandroid.data.fake.FakeNiANetwork
import com.example.mynowinandroid.data.fake.FakeTopicsRepository
import com.example.mynowinandroid.data.network.NiANetwork
import com.example.mynowinandroid.data.news.NewsRepository
import com.example.mynowinandroid.data.news.TopicsRepository
import com.example.mynowinandroid.data.nowinandroid.UserPreferences
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
    fun bindsNiANetwork(fakeNiANetwork: FakeNiANetwork): NiANetwork

    @Binds
    fun bindsTopicRepository(fakeTopicsRepository: FakeTopicsRepository): TopicsRepository

    @Binds
    fun bindsNewsResourceRepository(fakeNewsRepository: FakeNewsRepository): NewsRepository

    @Binds
    fun bindsNiaDispatchers(defaultNiaDispatchers: DefaultNiaDispatchers): NiaDispatchers

    companion object {

        @Provides
        @Singleton
        fun provideUserPreferencesDataStore(
            @ApplicationContext context: Context,
            userPreferencesSerializer: UserPreferencesSerializer,
        ): DataStore<UserPreferences> =
            DataStoreFactory.create(serializer = userPreferencesSerializer) {
                context.dataStoreFile("user_preferences.pb")
            }

        @Provides
        @Singleton
        fun providesNetworkJson(): Json = Json {
            ignoreUnknownKeys = true
        }
    }
}
