package com.example.mynowinandroid.data

import androidx.datastore.core.CorruptionException
import com.example.mynowinandroid.data.nowinandroid.userPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import kotlin.test.assertEquals

class UserPreferencesSerializerTest {

    private val userPreferencesSerializer = UserPreferencesSerializer()

    @Test
    fun defaultUserPreferences_isEmpty() {
        assertEquals(userPreferences { }, userPreferencesSerializer.defaultValue)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun writingAndReadingUserPreferences_outputsCorrectValue() = runTest {
        val expectedUserPreferences = userPreferences {
            followedTopicsIds.add(0)
            followedTopicsIds.add(1)
        }

        val outputStream = ByteArrayOutputStream()

        expectedUserPreferences.writeTo(outputStream)

        val inputStream = ByteArrayInputStream(outputStream.toByteArray())

        val actualUserPreferences = userPreferencesSerializer.readFrom(inputStream)

        assertEquals(
            expectedUserPreferences,
            actualUserPreferences,
        )
    }

    @Test(expected = CorruptionException::class)
    fun readingInvalidUserPreferences_throwsCorruptionException() = runTest {
        userPreferencesSerializer.readFrom(ByteArrayInputStream(byteArrayOf(0)))
    }
}
