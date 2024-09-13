package com.tws.moments.data.source.api.tweet

import com.google.gson.annotations.SerializedName

data class TweetEntity(
    @SerializedName("sender")
    val sender: SenderEntity?,

    @SerializedName("content")
    val content: String?,

    @SerializedName("images")
    val images: List<ImageEntity>?,

    @SerializedName("comments")
    val comments: List<CommentEntity>?
)

data class CommentEntity(
    @SerializedName("sender")
    val sender: SenderEntity?,

    @SerializedName("content")
    val content: String?
)

data class SenderEntity(
    @SerializedName("nick")
    val nickname: String?,

    @SerializedName("username")
    val username: String?,

    @SerializedName("avatar")
    val avatarImgUrl: String?,
)

data class ImageEntity(
    @SerializedName("url")
    val url: String?
)