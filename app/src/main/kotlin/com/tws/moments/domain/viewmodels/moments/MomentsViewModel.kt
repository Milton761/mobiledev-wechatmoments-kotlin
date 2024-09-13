package com.tws.moments.domain.viewmodels.moments

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tws.moments.domain.model.profile.Profile
import com.tws.moments.domain.model.tweet.Tweet
import com.tws.moments.domain.usecases.GetProfile
import com.tws.moments.domain.usecases.GetTweets
import com.tws.moments.presentation.ui.moments.MomentsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Integer.min
import javax.inject.Inject

@HiltViewModel
class MomentsViewModel @Inject constructor(
    private val getProfile: GetProfile,
    private val getTweets: GetTweets,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MomentsState())
    val uiState: StateFlow<MomentsState> = _uiState.asStateFlow()

    fun getMoments(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val profile: Profile = getProfile(userId)
            val tweets: List<Tweet> = getTweets.execute(GetTweets.Params(userId))

            MomentsState(
                profile = profile,
                tweets = tweets,
                tweetsToShow = tweets.size.coerceAtMost(5)
            ).let { _uiState.value = it }
        }
    }

    fun loadMoreTweets() = viewModelScope.launch(Dispatchers.IO) {
        val moments = uiState.value.copy()
        moments.tweetsToShow = min(moments.tweets.size, moments.tweetsToShow + PAGE_SIZE)

        Log.d("GG", "more tweets ${moments.tweetsToShow}")

        _uiState.value = moments
    }

    companion object {
        const val PAGE_SIZE = 5
    }
}