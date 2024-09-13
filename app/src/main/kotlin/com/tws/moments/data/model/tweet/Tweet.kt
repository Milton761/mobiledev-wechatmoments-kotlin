package com.tws.moments.data.model.tweet

class Tweet(
    val sender: Sender?,
    val content: String?,
    val images: List<Image>?,
    val comments: List<Comment>?
)

class Sender(
    val nickname: String?,
    val username: String?,
    val avatarImgUrl: String?,
)

class Comment(
    val sender: Sender?,
    val content: String?
)

class Image(
    val url: String?
)