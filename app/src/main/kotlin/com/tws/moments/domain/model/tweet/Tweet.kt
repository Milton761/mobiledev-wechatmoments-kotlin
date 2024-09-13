package com.tws.moments.domain.model.tweet

data class Tweet(
    val sender: Sender?,
    val content: String?,
    val images: List<Image>?,
    val comments: List<Comment>?
)

data class Sender(
    val nickname: String?,
    val username: String?,
    val avatarImgUrl: String?,
)

data class Comment(
    val sender: Sender?,
    val content: String?
)

data class Image(
    val url: String
)