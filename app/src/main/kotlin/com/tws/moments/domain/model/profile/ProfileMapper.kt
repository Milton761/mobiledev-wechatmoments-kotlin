package com.tws.moments.domain.model.profile

object ProfileMapper {

    fun from(profile: com.tws.moments.data.model.profile.Profile) = Profile(
        profileImgUrl = profile.profileImgUrl,
        avatarImgUrl = profile.avatarImgUrl,
        nickname = profile.nickname,
        username = profile.username,
    )
}