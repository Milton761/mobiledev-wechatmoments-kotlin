package com.tws.moments.data.model.profile

import com.tws.moments.data.source.api.profile.ProfileEntity

object ProfileMapper {

    fun from(entity: ProfileEntity) = Profile(
        profileImgUrl = entity.profileImgUrl,
        avatarImgUrl = entity.avatarImgUrl,
        nickname = entity.nickname,
        username = entity.username,
    )
}