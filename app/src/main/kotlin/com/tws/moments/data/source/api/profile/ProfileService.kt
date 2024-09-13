package com.tws.moments.data.source.api.profile

import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileService {

    /** https://thoughtworks-ios.herokuapp.com/user/jsmith */
    @GET("user/{userId}")
    suspend fun getProfile(@Path("userId") userId: String): ProfileEntity
}