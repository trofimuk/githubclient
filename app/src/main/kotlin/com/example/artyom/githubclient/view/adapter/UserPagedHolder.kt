package com.example.artyom.githubclient.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.artyom.githubclient.R
import com.example.artyom.githubclient.domain.User
import kotlinx.android.synthetic.main.user_item.view.*

class UserPagedHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo(user: User?){
        itemView.tvUserLogin.text = user?.login
        itemView.tvId.text = user?.id.toString()
        Glide
            .with(itemView.context!!)
            .load(user?.urlAvatar)
            .into(itemView.imageViewAvatar)
    }

    companion object {
        fun create(parent: ViewGroup): UserPagedHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.user_item, parent,false)
            return UserPagedHolder(view)
        }
    }
}