package com.tws.moments.data.source.api.profile

import com.google.gson.annotations.SerializedName


data class ProfileEntity(
    @SerializedName("profile-image")
    val profileImgUrl: String?,

    @SerializedName("avatar")
    val avatarImgUrl: String?,

    @SerializedName("nick")
    val nickname: String?,

    @SerializedName("username")
    val username: String?,
)