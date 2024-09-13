package com.tws.moments.presentation.ui.moments

import com.tws.moments.domain.model.tweet.Tweet
import com.tws.moments.domain.model.profile.Profile

data class MomentsState(
    var profile: Profile? = null,
    var tweets: List<Tweet> = emptyList(),
    var tweetsToShow: Int = 0,
)