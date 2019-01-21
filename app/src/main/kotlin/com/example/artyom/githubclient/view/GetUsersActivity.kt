package com.example.artyom.githubclient


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.artyom.githubclient.domain.User
import com.example.artyom.githubclient.view.UserViewModel
import com.example.artyom.githubclient.view.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_main.*

class GetUsersActivity : AppCompatActivity() {
    private val users = ArrayList<User>()

    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

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
        userAdapter = UserAdapter(users)
        usersRecyclerView.adapter = userAdapter
    }

    private fun populateUserAdapter(){
        userViewModel.getUserList()?.observe(this, Observer { usersList ->
            usersList?.forEach {
                users.add(it)
            }
            userAdapter.notifyDataSetChanged()
        })
    }
}
