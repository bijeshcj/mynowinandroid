package com.example.mynowinandroid.data.news.fake

import com.example.mynowinandroid.di.DefaultNiaDispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FakeNewsRepositoryTest {

    private lateinit var subject: FakeNewsRepository

    @Before
    fun setup() {
        subject = FakeNewsRepository(
            dispatchers = DefaultNiaDispatchers(),
            networkJson = Json { ignoreUnknownKeys = true },
        )
    }

    @Test
    fun testDeserializationOfNewsResources() = runBlocking {
        assertEquals(
            FakeDataSource.sampleResource,
            subject.getNewsResourcesStream().first().first(),
        )
    }
}
