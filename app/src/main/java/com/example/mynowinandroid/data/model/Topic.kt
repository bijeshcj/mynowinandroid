package com.example.mynowinandroid.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    val id: Int,
    val name: String,
    val description: String,
    val followed: Boolean = false
)
