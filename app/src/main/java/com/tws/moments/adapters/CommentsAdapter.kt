package com.tws.moments.adapters

import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.tws.moments.databinding.ItemCommentBinding
import com.tws.moments.domain.model.tweet.Comment
import com.tws.moments.utils.clickableSpan

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentHolder>() {

    var comments: List<Comment> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCommentBinding.inflate(inflater, parent, false)

        return CommentHolder(binding)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        holder.bind(comments[position])
    }

    inner class CommentHolder(
        private val binding: ItemCommentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: Comment) {

            val spannableString = comment.sender?.nickname?.clickableSpan {
                Toast.makeText(it.context, "${comment.sender.username} info.", Toast.LENGTH_SHORT).show()
            }

            binding.tvSimpleComment.text = spannableString
            binding.tvSimpleComment.append(":" + (comment.content ?: ""))
            binding.tvSimpleComment.movementMethod = LinkMovementMethod.getInstance()
        }
    }
}
