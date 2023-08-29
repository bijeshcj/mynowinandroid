package com.example.mynowinandroid.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.example.mynowinandroid.data.UserPreferencesSerializer
import com.example.mynowinandroid.data.fake.FakeNewsRepository
import com.example.mynowinandroid.data.fake.FakeTopicsRepository
import com.example.mynowinandroid.data.news.NewsRepository
import com.example.mynowinandroid.data.news.TopicsRepository
import com.example.mynowinandroid.data.nowinandroid.UserPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.serialization.json.Json
import org.junit.rules.TemporaryFolder
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class],
)
interface TestAppModule {

    @Binds
    fun bindsTopicRepository(fakeTopicsRepository: FakeTopicsRepository): TopicsRepository

    @Binds
    fun bindsNewsResourceRepository(fakeNewsRepository: FakeNewsRepository): NewsRepository

    @Binds
    fun bindsNiaDispatchers(defaultNiaDispatchers: DefaultNiaDispatchers): NiaDispatchers

    companion object {
        @Provides
        @Singleton
        fun providesUserPreferencesDataStore(
            userPreferencesSerializer: UserPreferencesSerializer,
            tmpFolder: TemporaryFolder,
        ): DataStore<UserPreferences> {
            return DataStoreFactory.create(serializer = userPreferencesSerializer) {
                tmpFolder.newFile("user_preferences_test.pb")
            }
        }

        @Provides
        @Singleton
        fun providesNetworkJson(): Json = Json {
            ignoreUnknownKeys = true
        }
    }
}
