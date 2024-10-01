package com.tws.moments.presentation.viewmodels

import com.tws.moments.domain.model.profile.Profile
import com.tws.moments.domain.model.tweet.Tweet
import com.tws.moments.domain.usecases.GetProfile
import com.tws.moments.domain.usecases.GetTweets
import com.tws.moments.presentation.viewmodels.moments.MomentsViewModel
import com.tws.moments.presentation.viewmodels.moments.MomentsViewModel.Companion.PAGE_SIZE
import com.tws.moments.presentation.ui.moments.MomentsState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MomentsViewModelTest {

    private val getProfile: GetProfile = mockk()
    private val getTweets: GetTweets = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `listen viewModel should update a empty state`() = runTest {
        // Arrange
        val viewModel = MomentsViewModel(getProfile, getTweets)

        // Act
        // nothing to do, just listen the state

        // Assert
        assertEquals(
            viewModel.uiState.value, MomentsState(
                profile = null,
                tweets = emptyList(),
                tweetsToShow = 0
            )
        )
    }

    @Test
    fun `getMoments should update uiState with profile and tweets, case 0`() = runTest {
        // Arrange
        val userId = "username"
        val profile = Profile(
            profileImgUrl = "profile_image_url",
            avatarImgUrl = "avatar_image_url",
            nickname = "nickname",
            username = "username"
        )
        val tweets = emptyList<Tweet>() // case 0: no tweets

        coEvery { getProfile(userId) } returns profile
        coEvery { getTweets(userId) } returns tweets

        val viewModel = MomentsViewModel(getProfile, getTweets)

        // Act
        viewModel.getMoments(userId)

        // Assert
        assertEquals(
            viewModel.uiState.first(), MomentsState(
                profile = profile,
                tweets = tweets,
                tweetsToShow = 0
            )
        )
    }

    @Test
    fun `getMoments should update uiState with profile and tweets, case 1`() = runTest {
        // Arrange
        val userId = "username"
        val profile = Profile(
            profileImgUrl = "profile_image_url",
            avatarImgUrl = "avatar_image_url",
            nickname = "nickname",
            username = "username"
        )

        val tam = PAGE_SIZE - 1 // case 1: tweets.size < PAGE.SIZE
        val tweets = List(tam) { mockk<Tweet>() }

        coEvery { getProfile(userId) } returns profile
        coEvery { getTweets(userId) } returns tweets

        val viewModel = MomentsViewModel(getProfile, getTweets)

        // Act
        viewModel.getMoments(userId)

        // Assert
        assertEquals(
            viewModel.uiState.value, MomentsState(
                profile = profile,
                tweets = tweets,
                tweetsToShow = tam
            )
        )
    }

    @Test
    fun `getMoments should update uiState with profile and tweets, case 2`() = runTest {
        // Arrange
        val userId = "username"
        val profile = Profile(
            profileImgUrl = "profile_image_url",
            avatarImgUrl = "avatar_image_url",
            nickname = "nickname",
            username = "username"
        )

        val tam = PAGE_SIZE // case 1: tweets.size == PAGE.SIZE
        val tweets = List(tam) { mockk<Tweet>() }

        coEvery { getProfile(userId) } returns profile
        coEvery { getTweets(userId) } returns tweets

        val viewModel = MomentsViewModel(getProfile, getTweets)

        // Act
        viewModel.getMoments(userId)

        // Assert
        assertEquals(
            viewModel.uiState.value, MomentsState(
                profile = profile,
                tweets = tweets,
                tweetsToShow = PAGE_SIZE
            )
        )
    }

    @Test
    fun `getMoments should update uiState with profile and tweets, case 3`() = runTest {
        // Arrange
        val userId = "username"
        val profile = Profile(
            profileImgUrl = "profile_image_url",
            avatarImgUrl = "avatar_image_url",
            nickname = "nickname",
            username = "username"
        )

        val tam = PAGE_SIZE // case 3: tweets.size > PAGE.SIZE
        val tweets = List(tam) { mockk<Tweet>() }

        coEvery { getProfile(userId) } returns profile
        coEvery { getTweets(userId) } returns tweets

        val viewModel = MomentsViewModel(getProfile, getTweets)

        // Act
        viewModel.getMoments(userId)

        // Assert
        assertEquals(
            viewModel.uiState.value, MomentsState(
                profile = profile,
                tweets = tweets,
                tweetsToShow = PAGE_SIZE
            )
        )
    }

    @Test
    fun `loadMoreTweets should update uiState with tweets to show`() = runTest {
        // Arrange
        val userId = "username"
        val profile = Profile(
            profileImgUrl = "profile_image_url",
            avatarImgUrl = "avatar_image_url",
            nickname = "nickname",
            username = "username"
        )

        val tam = PAGE_SIZE * 2
        val tweets = List(tam) { mockk<Tweet>() }

        coEvery { getProfile(userId) } returns profile
        coEvery { getTweets(userId) } returns tweets

        val viewModel = MomentsViewModel(getProfile, getTweets)

        // Act and Assert
        viewModel.getMoments(userId)
        assertEquals(5, viewModel.uiState.value.tweetsToShow)

        viewModel.loadMoreTweets()
        assertEquals(10, viewModel.uiState.value.tweetsToShow)

        viewModel.loadMoreTweets()
        assertEquals(10, viewModel.uiState.value.tweetsToShow)
    }
}