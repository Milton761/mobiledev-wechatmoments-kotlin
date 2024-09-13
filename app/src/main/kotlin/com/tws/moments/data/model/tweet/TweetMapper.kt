package com.tws.moments.data.model.tweet

import com.tws.moments.data.source.api.tweet.CommentEntity
import com.tws.moments.data.source.api.tweet.ImageEntity
import com.tws.moments.data.source.api.tweet.SenderEntity
import com.tws.moments.data.source.api.tweet.TweetEntity

object TweetMapper {

    fun from(entity: TweetEntity): Tweet {
        return Tweet(
            sender = entity.sender?.let(SenderMapper::from),
            content = entity.content,
            images = entity.images?.map(ImageMapper::from),
            comments = entity.comments?.map(CommentMapper::from)
        )
    }
}

object SenderMapper {

    fun from(entity: SenderEntity) = Sender(
        nickname = entity.nickname,
        username = entity.username,
        avatarImgUrl = entity.avatarImgUrl
    )
}

object CommentMapper {

    fun from(entity: CommentEntity) = Comment(
        sender = entity.sender?.let(SenderMapper::from),
        content = entity.content
    )
}

object ImageMapper {

    fun from(entity: ImageEntity) = Image(
        url = entity.url
    )
}