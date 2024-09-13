package com.tws.moments.domain.usecases

import com.tws.moments.data.repository.TweetRepository
import com.tws.moments.domain.model.tweet.Tweet
import com.tws.moments.domain.model.tweet.TweetMapper
import javax.inject.Inject

class GetTweets @Inject constructor(
    private val repository: TweetRepository,
) : UseCase<GetTweets.Params, List<Tweet>> {

    class Params(val userId: String) : UseCaseParams

    override suspend fun execute(params: Params): List<Tweet> {

        return repository.getTweets(
            userId = params.userId
        ).map(TweetMapper::from)
    }
}