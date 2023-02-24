package com.example.mynowinandroid.data.news

import java.util.Date

data class NewsResource(
    val episode:Int,
    val title: String,
    val content:String,
    val url:String,
    val authorName:String,
    val publishDate: Date,
    val type:String,
    val topics: List<String>,

)

data class VideoInfo(
    val url:String,
    val startTimestamp: String,
    val endTimeStamp: String
)