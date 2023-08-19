package com.example.mynowinandroid.data.fake

import com.example.mynowinandroid.data.network.NetworkNewsResource
import com.example.mynowinandroid.data.network.NetworkTopic
import com.example.mynowinandroid.data.network.NiANetwork
import com.example.mynowinandroid.di.NiaDispatchers
import com.google.samples.apps.nowinandroid.data.fake.FakeDataSource
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class FakeNiANetwork(
    private val dispatchers: NiaDispatchers,
    private val networkJson: Json,
) : NiANetwork {
//    override suspend fun getTopics(): List<NetworkNewsResource> = withContext(dispatchers.IO){
//        networkJson.decodeFromString<ResourceData>(FakeDataSource.data).resources
//    }

    override suspend fun getNewsResources(): List<NetworkNewsResource> =
        withContext(dispatchers.IO) {
            networkJson.decodeFromString<ResourceData>(FakeDataSource.data).resources
        }
}

@Serializable
private data class ResourceData(
    val resources: List<NetworkNewsResource>,
)
