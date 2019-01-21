package com.example.artyom.githubclient.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.artyom.githubclient.R
import com.example.artyom.githubclient.domain.User
import kotlinx.android.synthetic.main.user_item.view.*
import java.util.ArrayList


class UserAdapter(private val userArrayList: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.UsersHolder>() {

    override fun getItemCount(): Int = userArrayList.size

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UsersHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        context = parent.context
        return UserAdapter.UsersHolder(v)
    }

    override fun onBindViewHolder(usersHolder: UsersHolder, position: Int) {
        usersHolder.itemView.tag = position
        usersHolder.tvUserLogin.text = userArrayList[position].login
        usersHolder.twId.text = userArrayList[position].id
        Glide
            .with(context!!)
            .load(userArrayList[position].urlAvatar)
            .into(usersHolder.imageViewAvatar)
    }

    class UsersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvUserLogin = itemView.tvUserLogin!!
        var twId =  itemView.tvId!!
        var imageViewAvatar = itemView.imageViewAvatar!!
    }


}
