package com.example.mynowinandroid.data.news

import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    val id: Int,
    val name: String,
    val description: String,
)
