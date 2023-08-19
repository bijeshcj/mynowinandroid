package com.example.mynowinandroid.data.fake

import com.example.mynowinandroid.di.DefaultNiaDispatchers
import com.google.samples.apps.nowinandroid.data.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FakeNiANetworkTest {

    private lateinit var subject: FakeNiANetwork

    @Before
    fun setup() {
        subject = FakeNiANetwork(
            dispatchers = DefaultNiaDispatchers(),
            networkJson = Json { ignoreUnknownKeys = true },
        )
    }

    @Test
    fun testDeserializationOfTopics() = runTest {
        assertEquals(
            FakeDataSource.sampleResource,
            subject.getNewsResources().first(),
        )
    }

    @Test
    fun testDeserializationOfNewsResources() = runTest {
        assertEquals(
            FakeDataSource.sampleResource,
            subject.getNewsResources().first(),
        )
    }
}
