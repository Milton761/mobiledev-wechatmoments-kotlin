package com.tws.moments.data.mappers

import com.tws.moments.data.model.profile.ProfileMapper
import com.tws.moments.data.source.api.profile.ProfileEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class ProfileMapperTest {

    @Test
    fun `given a ProfileEntity, when call from, return a Profile`() {
        val entity = ProfileEntity(
            profileImgUrl = "profileImgUrl",
            nickname = "nickname",
            username = "username",
            avatarImgUrl = "avatarImgUrl"
        )

        val profile = ProfileMapper.from(entity)

        assertEquals(entity.profileImgUrl, profile.profileImgUrl)
        assertEquals(entity.nickname, profile.nickname)
        assertEquals(entity.username, profile.username)
        assertEquals(entity.avatarImgUrl, profile.avatarImgUrl)
    }
}