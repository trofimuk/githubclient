package com.example.artyom.githubclient.view.activity


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.artyom.githubclient.R
import com.example.artyom.githubclient.domain.User
import com.example.artyom.githubclient.view.view_model.UserViewModel
import com.example.artyom.githubclient.view.adapter.UserPagedAdapter
import kotlinx.android.synthetic.main.activity_main.*

class GetUsersActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var userPagedAdapter: UserPagedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initUserAdapter()

        populateUserAdapter()
    }

    private fun initViewModel(){
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.let {  lifecycle.addObserver(it) }
    }

    private fun initUserAdapter(){
        usersRecyclerView.layoutManager = LinearLayoutManager(this)
        userPagedAdapter = UserPagedAdapter()
        usersRecyclerView.adapter = userPagedAdapter
    }

    private fun populateUserAdapter(){
        userViewModel.liveDataUserList.observe(this, Observer<PagedList<User>> { userPagedAdapter.submitList(it) })
    }
}
