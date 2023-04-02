package com.example.mynowinandroid.data.news.fake

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FakeNewsRepositoryTest {

    private lateinit var subject: FakeNewsRepository

    @Before
    fun setup(){
        subject = FakeNewsRepository(ioDispatcher = TestCoroutineDispatcher())
    }

    @Test
    fun testDeserializationOfNewsResources() = runBlocking {
        assertEquals(
            FakeDataSource.sampleResource,
            subject.monitor().first().first()
        )
    }



}