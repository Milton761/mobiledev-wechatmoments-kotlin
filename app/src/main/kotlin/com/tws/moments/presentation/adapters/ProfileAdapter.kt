package com.tws.moments.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tws.moments.R
import com.tws.moments.databinding.ItemMomentHeadBinding
import com.tws.moments.domain.model.profile.Profile
import com.tws.moments.imageloader.GlideImageLoader

class ProfileAdapter(

) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    var profile: Profile? = null
        set(value) {
            field = value
            notifyItemChanged(0)
        }

    class ViewHolder(
        private val binding: ItemMomentHeadBinding,
        context: Context,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val imageLoader = GlideImageLoader(context)

        fun bind(profile: Profile) {


            binding.tvUserNickname.text = profile.username

            imageLoader.displayImage(profile.profileImgUrl, binding.ivUserProfile)
            imageLoader.displayImage(profile.avatarImgUrl, binding.ivUserAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMomentHeadBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, parent.context)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_moment_head
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        profile?.let(holder::bind)
    }
}