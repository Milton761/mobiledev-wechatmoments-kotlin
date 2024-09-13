package com.tws.moments.presentation.ui.moments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.tws.moments.databinding.FragmentMomentsBinding
import com.tws.moments.domain.model.tweet.Tweet
import com.tws.moments.domain.viewmodels.moments.MomentsViewModel
import com.tws.moments.presentation.adapters.ProfileAdapter
import com.tws.moments.presentation.adapters.TweetAdapter
import com.tws.moments.utils.dip
import com.tws.moments.views.LoadMoreListener
import com.tws.moments.views.itemdecoration.MomentDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MomentsFragment : Fragment() {

    private lateinit var binding: FragmentMomentsBinding

    private val viewModel: MomentsViewModel by viewModels()

    private val profileAdapter = ProfileAdapter()
    private val tweetAdapter = TweetAdapter()

    private val momentAdapter = ConcatAdapter(
        profileAdapter,
        tweetAdapter,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    Log.d("GG", it.profile?.username.toString())

                    val tweets: List<Tweet> = it.tweets
                        .subList(0, it.tweetsToShow)

                    profileAdapter.profile = it.profile
                    tweetAdapter.tweets = tweets.toMutableList()
                    momentAdapter.notifyDataSetChanged()

                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        }

        viewModel.getMoments("jsmith")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMomentsBinding.inflate(layoutInflater, container, false)

        setupRecyclerView()


        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(
            MomentDividerItemDecoration(
                offsets = requireContext().dip(10),
                dividerColor = Color.parseColor("#dddddd"),
                startPosition = 1
            )
        )

        binding.swipeRefreshLayout.isRefreshing = true
        binding.recyclerView.adapter = momentAdapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getMoments("jsmith")
        }

        // like this!
        binding.recyclerView.addOnScrollListener(object : LoadMoreListener() {
            override fun onLoadMore() {
                viewModel.loadMoreTweets()
            }
        })
    }

    companion object {

        fun newInstance() = MomentsFragment()
    }
}