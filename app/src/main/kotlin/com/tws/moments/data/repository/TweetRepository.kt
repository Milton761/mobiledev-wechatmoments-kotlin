package com.tws.moments.data.repository

import com.tws.moments.app.DispatcherAgent
import com.tws.moments.data.model.tweet.Tweet
import com.tws.moments.data.source.api.tweet.TweetsSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TweetRepository @Inject constructor(
    private val api: TweetsSource,
    private val dispatcherAgent: DispatcherAgent,
) {

    suspend fun getTweets(userId: String): List<Tweet> = withContext(dispatcherAgent.io) {
        return@withContext api.getTweets(userId)
    }
}