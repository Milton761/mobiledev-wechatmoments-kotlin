package com.tws.moments.data.source.api.profile

import com.tws.moments.data.model.profile.Profile
import com.tws.moments.data.model.profile.ProfileMapper
import com.tws.moments.data.source.DataSource
import com.tws.moments.data.source.Source
import javax.inject.Inject

class ProfileSource @Inject constructor(
    private val profileService: ProfileService,
) : DataSource {

    override val type: Source = Source.API

    // TODO add error handling
    suspend fun getProfile(userId: String): Profile {
        return profileService
            .getProfile(userId)
            .let(ProfileMapper::from)
    }
}