package com.example.mynowinandroid.data.news

import kotlinx.datetime.Instant
import kotlinx.datetime.toInstant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@kotlinx.serialization.Serializable
data class NewsResource(
    val episode:Int,
    val title: String,
    val content:String,
    @SerialName("URL")
    val url:String,
    val authorName:String,
    @kotlinx.serialization.Serializable(InstantSerializer::class)
    val publishDate: Instant,
    val type:String,
    val topics: List<String>,
    val alternateVideo: VideoInfo?
)
@kotlinx.serialization.Serializable
data class VideoInfo(
    @SerialName("URL")
    val url:String,
    val startTimestamp: Int,
    val endTimestamp: Int
)

private object InstantSerializer : KSerializer<Instant>{
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "Instant",
        kind = PrimitiveKind.STRING
    )


    override fun deserialize(decoder: Decoder): Instant = decoder.decodeString().toInstant()

    override fun serialize(encoder: Encoder, value: Instant) = encoder.encodeString(value.toString())

}