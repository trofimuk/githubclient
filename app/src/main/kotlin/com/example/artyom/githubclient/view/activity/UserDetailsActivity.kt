package com.example.artyom.githubclient.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.artyom.githubclient.R
import com.example.artyom.githubclient.view.view_model.UserDetailsViewModel
import kotlinx.android.synthetic.main.user_details_activity.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var userDetailsViewModel: UserDetailsViewModel
    private var login : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_details_activity)
        initViewModel()
    }

    override fun onStart() {
        super.onStart()
        val intentUserDetail = intent
        if (intentUserDetail.getStringExtra("login") != null) {
            loadDate(intentUserDetail.getStringExtra("login"))
        }
    }

    private fun initViewModel(){
        userDetailsViewModel = ViewModelProviders.of(this).get(UserDetailsViewModel::class.java)
        userDetailsViewModel.let {  lifecycle.addObserver(it) }
    }

    private fun loadDate(login: String){
        userDetailsViewModel.getUserDetails(login)?.observe(this, Observer { userDetails ->
            Glide
                .with(applicationContext)
                .load(userDetails?.urlAvatar)
                .into(imageViewAvatarUserDetail)

            twNameUserDetail.text = userDetails?.name
            twEmailUserDetail.text = userDetails?.email
            twCompanyUserDetail.text = userDetails?.company
            twFollowersUserDetail.text = userDetails?.countFollowers
            twFollowingUserDetail.text = userDetails?.countFollowing

            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            try {
                val date = dateFormat.parse(userDetails?.dateCreatedAccount)
                twDateCreatedUserDetail.text = date.toString()
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        })
    }
}
