package com.example.mynowinandroid.data.network

interface NiANetwork {

    suspend fun getNewsResources(): List<NetworkNewsResource>
}
