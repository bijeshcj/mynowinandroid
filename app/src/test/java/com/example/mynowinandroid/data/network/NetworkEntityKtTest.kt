package com.example.mynowinandroid.data.network

import com.example.mynowinandroid.data.model.NewsResourceType
import kotlinx.datetime.Instant
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

class NetworkEntityKtTest {

    @Test
    fun network_author_can_be_mapped_to_author_entity() {
        val networkModel = NetworkAuthor(
            id = 0,
            name = "Test",
            imageUrl = "something",
        )
        val entity = networkModel.asEntity()
        assertEquals(0, entity.id)
        assertEquals("Test", entity.name)
        assertEquals("something", entity.imageUrl)
    }

    @Test
    fun network_topic_can_be_mapped_to_topic_entity() {
        val networkModel = NetworkTopic(
            id = 0,
            name = "Test",
            description = "something",
        )
        val entity = networkModel.asEntity()
        assertEquals(0, entity.id)
        assertEquals("Test", entity.name)
        assertEquals("something", entity.description)
    }

    @Test
    fun network_news_resource_can_be_mapped_to_news_resource_entity() {
        val networkModel = NetworkNewsResource(
            id = 0,
            episodeId = 2,
            title = "title",
            content = "content",
            url = "url",
            publishDate = Instant.fromEpochMilliseconds(1),
            type = NewsResourceType.Article.displayText,
        )
        val entity = networkModel.asEntity()

        Assert.assertEquals(0, entity.id)
        Assert.assertEquals(2, entity.episodeId)
        Assert.assertEquals("title", entity.title)
        Assert.assertEquals("content", entity.content)
        Assert.assertEquals("url", entity.url)
        Assert.assertEquals(Instant.fromEpochMilliseconds(1), entity.publishDate)
        assertEquals(NewsResourceType.Article.displayText, entity.type)

        val expandedNetworkModel = NetworkNewsResourceExpanded(
            id = 0,
            episodeId = 2,
            title = "title",
            content = "content",
            url = "url",
            publishDate = Instant.fromEpochMilliseconds(1),
            type = NewsResourceType.Article.displayText,
        )

        val entityFromExpanded = expandedNetworkModel.asEntity()

        Assert.assertEquals(0, entityFromExpanded.id)
        Assert.assertEquals(2, entityFromExpanded.episodeId)
        Assert.assertEquals("title", entityFromExpanded.title)
        Assert.assertEquals("content", entityFromExpanded.content)
        Assert.assertEquals("url", entityFromExpanded.url)
        Assert.assertEquals(Instant.fromEpochMilliseconds(1), entityFromExpanded.publishDate)
        assertEquals(NewsResourceType.Article.displayText, entityFromExpanded.type)
    }

    @Test
    fun network_episode_can_be_mapped_to_episode_entity() {
        val networkModel = NetworkEpisode(
            id = 0,
            name = "name",
            publishDate = Instant.fromEpochMilliseconds(1),
            alternateVideo = "alternateVideo",
            alternateAudio = "alternateAudio",
        )
        val entity = networkModel.asEntity()

        Assert.assertEquals(0, entity.id)
        Assert.assertEquals("name", entity.name)
        Assert.assertEquals("alternateVideo", entity.alternateVideo)
        Assert.assertEquals("alternateAudio", entity.alternateAudio)
        Assert.assertEquals(Instant.fromEpochMilliseconds(1), entity.publishDate)

        val expandedNetworkModel = NetworkEpisodeExpanded(
            id = 0,
            name = "name",
            publishDate = Instant.fromEpochMilliseconds(1),
            alternateVideo = "alternateVideo",
            alternateAudio = "alternateAudio",
        )

        val entityFromExpanded = expandedNetworkModel.asEntity()

        Assert.assertEquals(0, entityFromExpanded.id)
        Assert.assertEquals("name", entityFromExpanded.name)
        Assert.assertEquals("alternateVideo", entityFromExpanded.alternateVideo)
        Assert.assertEquals("alternateAudio", entityFromExpanded.alternateAudio)
        Assert.assertEquals(Instant.fromEpochMilliseconds(1), entityFromExpanded.publishDate)
    }
}
