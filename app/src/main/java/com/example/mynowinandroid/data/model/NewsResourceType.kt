package com.example.mynowinandroid.data.model

enum class NewsResourceType(
    val displayText: String,
    val description: String,
) {

    Video(
        displayText = "Video",
        description = "A video published on YouTube",
    ),
    APIChange(
        displayText = "API Change",
        description = "An addition, deprecation or change to the Android platform APIs.",
    ),
    Article(
        displayText = "Article",
        description = "An article, typically on Medium or the official Android blog",
    ),
    Codelab(
        displayText = "Docs",
        description = "A new or updated piece of documentation",
    ),
    Podcast(
        displayText = "Podcast 🎙",
        description = "A podcast",
    ),
    Docs(
        displayText = "Docs 📑",
        description = "A new or updated piece of documentation",
    ),
    Event(
        displayText = "Event 📆",
        description = "Information about a developer event e.g. Android Developer Summit",
    ),
    DAC(
        displayText = "DAC",
        description = "Android version features - Information about features in an Android",
    ),
    Unknown(
        displayText = "Unknown",
        description = "Unknown",
    ),
}
