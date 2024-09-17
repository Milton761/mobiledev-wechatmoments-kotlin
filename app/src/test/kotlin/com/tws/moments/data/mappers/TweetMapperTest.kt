package com.tws.moments.data.mappers

import com.tws.moments.data.model.tweet.Comment
import com.tws.moments.data.model.tweet.CommentMapper
import com.tws.moments.data.model.tweet.Image
import com.tws.moments.data.model.tweet.ImageMapper
import com.tws.moments.data.model.tweet.Sender
import com.tws.moments.data.model.tweet.SenderMapper
import com.tws.moments.data.model.tweet.Tweet
import com.tws.moments.data.model.tweet.TweetMapper
import com.tws.moments.data.source.api.tweet.CommentEntity
import com.tws.moments.data.source.api.tweet.ImageEntity
import com.tws.moments.data.source.api.tweet.SenderEntity
import com.tws.moments.data.source.api.tweet.TweetEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class TweetMapperTest {

    private val senderEntity = SenderEntity(
        nickname = "nickname",
        username = "username",
        avatarImgUrl = "avatarImgUrl",
    )

    private val imageEntity = ImageEntity(
        url = "url",
    )

    private val commentEntity = CommentEntity(
        sender = senderEntity,
        content = "content",
    )

    private val tweetEntity = TweetEntity(
        sender = senderEntity,
        content = "content",
        images = listOf(imageEntity),
        comments = listOf(commentEntity),
    )

    @Test
    fun `given a TweetMapper, when call from, return a Tweet`() {
        val tweet: Tweet = TweetMapper.from(tweetEntity)

        assertEquals(tweet.sender?.nickname, commentEntity.sender?.nickname)
        assertEquals(tweet.sender?.username, commentEntity.sender?.username)
        assertEquals(tweet.sender?.avatarImgUrl, commentEntity.sender?.avatarImgUrl)

        assertEquals(tweet.content, tweetEntity.content)

        tweet.images
            ?.zip(tweetEntity.images.orEmpty())
            ?.forEach { (tweetImage, tweetEntityImage) ->
                assertEquals(tweetImage.url, tweetEntityImage.url)
            }

        tweet.comments
            ?.zip(tweetEntity.comments.orEmpty())
            ?.forEach { (tweetComment, tweetEntityComment) ->
                assertEquals(tweetComment.sender?.nickname, tweetEntityComment.sender?.nickname)
                assertEquals(tweetComment.sender?.username, tweetEntityComment.sender?.username)
                assertEquals(tweetComment.sender?.avatarImgUrl, tweetEntityComment.sender?.avatarImgUrl)
                assertEquals(tweetComment.content, tweetEntityComment.content)
            }
    }

    @Test
    fun `given a SenderMapper, when call from, return a Sender`() {
        val sender: Sender = SenderMapper.from(senderEntity)

        assertEquals(sender.nickname, senderEntity.nickname)
        assertEquals(sender.username, senderEntity.username)
        assertEquals(sender.avatarImgUrl, senderEntity.avatarImgUrl)
    }


    @Test
    fun `given a CommentMapper, when call from, return a Comment`() {
        val comment: Comment = CommentMapper.from(commentEntity)

        assertEquals(comment.sender?.nickname, commentEntity.sender?.nickname)
        assertEquals(comment.sender?.username, commentEntity.sender?.username)
        assertEquals(comment.sender?.avatarImgUrl, commentEntity.sender?.avatarImgUrl)

        assertEquals(comment.content, commentEntity.content)
    }

    @Test
    fun `given a ImageMapper, when call from, return a Image`() {
        val image: Image = ImageMapper.from(imageEntity)

        assertEquals(imageEntity.url, image.url)
    }
}