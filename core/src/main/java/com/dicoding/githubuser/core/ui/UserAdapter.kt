package com.dicoding.githubuser.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.githubuser.core.databinding.ItemUserBinding
import com.dicoding.githubuser.core.domain.model.GitHubUser

class UserAdapter(private val listener: ItemUserListener) :
    ListAdapter<GitHubUser, UserAdapter.UserViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: GitHubUser) {
            binding.apply {
                Glide.with(root.context)
                    .load(user.avatarUrl)
                    .into(sivAvatar)
                tvUsername.text = user.username
                root.setOnClickListener {
                    listener.onUserClicked(user.username)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GitHubUser>() {
            override fun areItemsTheSame(
                oldItem: GitHubUser,
                newItem: GitHubUser
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: GitHubUser,
                newItem: GitHubUser
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface ItemUserListener {
        fun onUserClicked(username: String)
    }
}