package com.tws.moments.data.source.api.tweet

import com.tws.moments.data.model.tweet.Tweet
import com.tws.moments.data.model.tweet.TweetMapper
import com.tws.moments.data.source.DataSource
import com.tws.moments.data.source.Source
import javax.inject.Inject

class TweetsSource @Inject constructor(
    private val tweetsService: TweetsService,
) : DataSource {

    override val type: Source = Source.API

    suspend fun getTweets(userId: String): List<Tweet> {

        return tweetsService
            .getTweets(userId)
            .filter(::isValidTweet)
            .map(TweetMapper::from)
    }

    private fun isValidTweet(tweet: TweetEntity): Boolean {
        return tweet.sender != null || tweet.content != null
    }
}