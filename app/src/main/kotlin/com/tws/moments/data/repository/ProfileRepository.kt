package com.tws.moments.data.repository

import com.tws.moments.app.DispatcherAgent
import com.tws.moments.data.model.profile.Profile
import com.tws.moments.data.source.api.profile.ProfileSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: ProfileSource,
    private val dispatcherAgent: DispatcherAgent,
) {

    suspend fun getProfile(userId: String): Profile = withContext(dispatcherAgent.io) {
        return@withContext api.getProfile(userId)
    }
}