package com.tws.moments.presentation.adapters

import android.content.Context
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tws.moments.R
import com.tws.moments.adapters.CommentsAdapter
import com.tws.moments.adapters.ImagesAdapter
import com.tws.moments.databinding.LayoutBaseTweetBinding
import com.tws.moments.domain.model.tweet.Comment
import com.tws.moments.domain.model.tweet.Image
import com.tws.moments.domain.model.tweet.Tweet
import com.tws.moments.imageloader.GlideImageLoader
import com.tws.moments.utils.dip
import com.tws.moments.views.itemdecoration.ImagesDecoration
import com.tws.moments.views.itemdecoration.MarginItemDecoration

class TweetAdapter(

) : RecyclerView.Adapter<TweetAdapter.ViewHolder>() {

    var tweets: MutableList<Tweet> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ViewHolder(
        private val binding: LayoutBaseTweetBinding,
        context: Context,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val imageLoader = GlideImageLoader(context)

        private val commentAdapter = CommentsAdapter()
        private val imageAdapter = ImagesAdapter()

        init {
            val margin: Int = itemView.context.dip(5)

            val marginDecorator = MarginItemDecoration(RecyclerView.VERTICAL, margin)
            binding.rvComments.addItemDecoration(marginDecorator)

            binding.imagesRecyclerView.addItemDecoration(ImagesDecoration(margin))
        }

        fun bind(tweet: Tweet) {
            binding.tvSenderNickname.text = tweet.sender?.nickname
            binding.tvTweetContent.text = tweet.content
            binding.tvTweetContent.movementMethod = LinkMovementMethod.getInstance()

            tweet.sender?.avatarImgUrl?.let(::bindAvatar)
            tweet.images.orEmpty().let(::bindImages)
            tweet.comments.orEmpty().let(::bindComments)
        }

        private fun bindImages(images: List<Image>) {
            binding.simpleImageView.isVisible = onlyOneImg(images)
            binding.imagesRecyclerView.isVisible = onlyOneImg(images).not()

            if (onlyOneImg(images)) {
                val imgUrl: String = images.first().url
                imageLoader.displayImage(imgUrl, binding.simpleImageView)
                binding.simpleImageView.tag = imgUrl

                return
            }

            imageAdapter.images = images.map(Image::url)

            binding.imagesRecyclerView.adapter = imageAdapter
            binding.imagesRecyclerView.layoutManager = getImageLayoutManager(images.size)
        }

        private fun bindComments(comments: List<Comment>) {
            commentAdapter.comments = comments

            binding.rvComments.adapter = commentAdapter
            binding.rvComments.layoutManager = LinearLayoutManager(binding.root.context)
        }

        private fun onlyOneImg(image: List<Image>) : Boolean = image.size == 1

        private fun bindAvatar(avatarUrl: String) {
            imageLoader.displayImage(avatarUrl, binding.ivSenderAvatar)
        }

        private fun getImageLayoutManager(imagesSize: Int): GridLayoutManager {
            return if (imagesSize == 4) {
                GridLayoutManager(itemView.context, 2, RecyclerView.HORIZONTAL, false)
            } else {
                GridLayoutManager(itemView.context, 3, RecyclerView.VERTICAL, false)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutBaseTweetBinding.inflate(inflater, parent, false)

        return ViewHolder(binding, parent.context)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.layout_base_tweet
    }

    override fun getItemCount(): Int {
        return tweets.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tweets[position])
    }

    fun add(tweets: List<Tweet>) {
        val newTweetSize: Int = tweets.size
        val oldTweetsSize: Int = this.tweets.size

        this.tweets.addAll(tweets)
        notifyItemRangeInserted(oldTweetsSize - 1, newTweetSize)
    }
}