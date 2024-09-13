package com.tws.moments.domain.usecases

import com.tws.moments.data.repository.ProfileRepository
import com.tws.moments.domain.model.profile.Profile
import com.tws.moments.domain.model.profile.ProfileMapper
import javax.inject.Inject

class GetProfile @Inject constructor(
    private val repository: ProfileRepository,
) : UseCase<GetProfile.Params, Profile> {

    class Params(val userId: String) : UseCaseParams

    override suspend fun execute(params: Params): Profile {

        return repository.getProfile(
            userId = params.userId,
        ).let(ProfileMapper::from)
    }

    suspend operator fun invoke(userId: String): Profile {
        return execute(Params(userId))
    }
}