package com.example.artyom.githubclient.data.remote

class RemoteContract {

    companion object {
        const val BASE_API_LAYER = "https://api.github.com/"

        const val USERS = "users"
        const val USER_DETAILS = "users/{login}"
        const val ID = "id"
        const val LOGIN = "login"
        const val URL_AVATAR = "avatar_url"

        const val SUCCESS = "success"

        const val SINCE = "since"
        const val PER_PAGE = "per_page"
        const val START_PER_PAGE :Long = 0

        const val NAME = "name"
        const val EMAIL = "email"
        const val COMPANY = "company"
        const val FOLLOWING  = "following"
        const val FOLLOWERS = "followers"
        const val CREATED_AT  = "created_at"
    }
}