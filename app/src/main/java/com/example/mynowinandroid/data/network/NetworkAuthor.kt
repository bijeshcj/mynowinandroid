package com.example.mynowinandroid.data.network

import com.example.mynowinandroid.data.local.entities.AuthorEntity

@kotlinx.serialization.Serializable
data class NetworkAuthor(
    val id: Int,
    val name: String,
    val imageUrl: String,
)

fun NetworkAuthor.asEntity() = AuthorEntity(
    id = id,
    name = name,
    imageUrl = imageUrl,
)
