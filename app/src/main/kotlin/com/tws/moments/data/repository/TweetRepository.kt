package com.tws.moments.data.repository

import com.tws.moments.data.model.tweet.Tweet
import com.tws.moments.data.source.api.tweet.TweetsSource
import javax.inject.Inject

class TweetRepository @Inject constructor(
    private val api: TweetsSource,
) {

    suspend fun getTweets(userId: String): List<Tweet> {
        return api.getTweets(userId)
    }
}