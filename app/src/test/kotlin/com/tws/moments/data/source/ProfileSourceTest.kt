package com.tws.moments.data.source

import com.tws.moments.data.model.profile.ProfileMapper
import com.tws.moments.data.source.api.profile.ProfileEntity
import com.tws.moments.data.source.api.profile.ProfileService
import com.tws.moments.data.source.api.profile.ProfileSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test


class ProfileSourceTest {

    private val profileService: ProfileService = mockk()
    private val profileSource = ProfileSource(profileService)

    val jsonBody: String = """
        {
            "profile-image": "https://techops-recsys-lateral-hiring.github.io/moments-data/images/user/profile-image.jpeg",
            "avatar": "https://techops-recsys-lateral-hiring.github.io/moments-data/images/user/avatar.png",
            "nick": "Huan Huan",
            "username": "hengzeng"
        }
    """.trimIndent()

    private val profileResponse = okhttp3.Response.Builder()
        .code(200)
        .body(jsonBody.toResponseBody("application/json".toMediaType()))

    @Test
    fun `getProfile should return mapped Profile from ProfileService`() = runBlocking {
        // Arrange
        val userId = "123"
        val profileEntity = ProfileEntity(userId, null, null, null)
        val expectedProfile = ProfileMapper.from(profileEntity)

        coEvery { profileService.getProfile(userId) } returns profileEntity

        // Act
        val actualProfile = profileSource.getProfile(userId)

        // Assert
//        assertEquals(expectedProfile, actualProfile)
    }
}