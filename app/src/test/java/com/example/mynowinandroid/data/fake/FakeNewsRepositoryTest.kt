package com.example.mynowinandroid.data.fake

import com.example.mynowinandroid.di.DefaultNiaDispatchers
import kotlinx.serialization.json.Json
import org.junit.Before

class FakeNewsRepositoryTest {
    private lateinit var subject: FakeNewsRepository

    @Before
    fun setup() {
        subject = FakeNewsRepository(
            dispatchers = DefaultNiaDispatchers(),
            networkJson = Json { ignoreUnknownKeys = true },
        )
    }
}
