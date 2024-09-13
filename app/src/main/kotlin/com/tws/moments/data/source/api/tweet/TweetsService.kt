package com.tws.moments.data.source.api.tweet

import retrofit2.http.GET
import retrofit2.http.Path

interface TweetsService {

    /** https://thoughtworks-ios.herokuapp.com/user/jsmith/tweets */
    @GET("user/{userId}/tweets")
    suspend fun getTweets(@Path("userId") userId: String): List<TweetEntity>
}