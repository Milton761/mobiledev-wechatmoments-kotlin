package com.tws.moments.domain.model.tweet

object TweetMapper {

    fun from(entity: com.tws.moments.data.model.tweet.Tweet) = Tweet(
        sender = entity.sender?.let(SenderMapper::from),
        content = entity.content,
        images = entity.images?.map(ImageMapper::from),
        comments = entity.comments?.map(CommentMapper::from)
    )
}

object CommentMapper {

    fun from(entity: com.tws.moments.data.model.tweet.Comment) = Comment(
        sender = entity.sender?.let(SenderMapper::from),
        content = entity.content
    )
}

object SenderMapper {

    fun from(entity: com.tws.moments.data.model.tweet.Sender) = Sender(
        nickname = entity.nickname,
        username = entity.username,
        avatarImgUrl = entity.avatarImgUrl
    )
}

object ImageMapper {

    fun from(entity: com.tws.moments.data.model.tweet.Image) = Image(
        url = entity.url ?: ""
    )
}