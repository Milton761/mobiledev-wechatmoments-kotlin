package com.tws.moments.data.repository

import com.tws.moments.data.model.profile.Profile
import com.tws.moments.data.source.api.profile.ProfileSource
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: ProfileSource,
) {

    suspend fun getProfile(userId: String): Profile {
        return api.getProfile(userId)
    }
}