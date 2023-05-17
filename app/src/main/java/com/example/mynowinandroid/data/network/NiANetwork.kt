package com.example.mynowinandroid.data.network

interface NiANetwork {

    suspend fun getTopics(): List<NetworkTopic>
    suspend fun getNewsResources(): List<NetworkNewsResource>
}
