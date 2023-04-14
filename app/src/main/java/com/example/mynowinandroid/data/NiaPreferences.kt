package com.example.mynowinandroid.data

import android.util.Log
import androidx.datastore.core.DataStore
import com.example.mynowinandroid.data.nowinandroid.UserPreferences
import com.example.mynowinandroid.data.nowinandroid.copy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry
import java.io.IOException
import javax.inject.Inject

class NiaPreferences @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>,
) {
    suspend fun setFollowedTopicIds(followedTopicIds: Set<Int>) {
        try {
            userPreferences.updateData {
                it.copy {
                    this.followedTopicsIds.clear()
                    this.followedTopicsIds.addAll(followedTopicIds)
                }
            }
        } catch (ioException: IOException) {
            Log.e("NiaPreferences", "Failed to update user preference", ioException)
        }
    }

    val followedTopicIds: Flow<Set<Int>> = userPreferences.data.retry {
        Log.e("NiaPreferences", "Failed to update user preference", it)
        true
    }.map {
        it.followedTopicsIdsList.toSet()
    }
}
