package com.tws.moments.presentation.model

import com.tws.moments.data.model.tweet.Tweet
import com.tws.moments.domain.model.profile.Profile

class Moments(
    val profile: Profile,
    val tweets: List<Tweet>,
)