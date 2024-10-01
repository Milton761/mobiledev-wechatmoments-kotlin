package com.tws.moments.data.source

import com.tws.moments.data.model.profile.ProfileMapper
import com.tws.moments.data.source.api.profile.ProfileEntity
import com.tws.moments.data.source.api.profile.ProfileService
import com.tws.moments.data.source.api.profile.ProfileSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ProfileSourceTest {

    private val profileService: ProfileService = mockk()
    private val profileSource = ProfileSource(profileService)

    @Test
    fun `getProfile should return mapped Profile from ProfileService`() = runBlocking {
        // Arrange
        val userId = "123"
        val profileEntity = ProfileEntity("profileImageUrl", "avatarImgUrl", "nickname", "userName")
        val expectedProfile = ProfileMapper.from(profileEntity)

        coEvery { profileService.getProfile(userId) } returns profileEntity

        // Act
        val actualProfile = profileSource.getProfile(userId)

        // Assert
        assertEquals(expectedProfile.username, actualProfile.username)
        assertEquals(expectedProfile.profileImgUrl, actualProfile.profileImgUrl)
        assertEquals(expectedProfile.avatarImgUrl, actualProfile.avatarImgUrl)
        assertEquals(expectedProfile.nickname, actualProfile.nickname)
    }
}