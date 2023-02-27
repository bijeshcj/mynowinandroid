package com.example.mynowinandroid.data.news.fake

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FakeNewsResourceRepositoryTest {

    private lateinit var subject: FakeNewsResourceRepository

    @Before
    fun setup(){
        subject = FakeNewsResourceRepository(ioDispatcher = TestCoroutineDispatcher())
    }

    @Test
    fun testDeserializationOfNewsResources() = runBlocking {
        assertEquals(
            FakeDataSource.sampleResource,
            subject.monitor().first().first()
        )
    }



}