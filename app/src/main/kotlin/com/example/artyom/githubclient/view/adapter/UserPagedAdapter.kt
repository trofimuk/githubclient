package com.example.artyom.githubclient.view.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.artyom.githubclient.domain.User
import android.support.v7.util.DiffUtil

class UserPagedAdapter : PagedListAdapter <User, RecyclerView.ViewHolder>(UserDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            :RecyclerView.ViewHolder = UserPagedHolder.create(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserPagedHolder).bindTo(getItem(position))
    }

    companion object {

        private val UserDiffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem
        }
    }
}