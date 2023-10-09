package com.example.testkt.api


import com.example.testkt.user.User
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("users")
    fun getUsers(): Call<UserResponse>
}

data class UserResponse (
    val data :List<User>
)
