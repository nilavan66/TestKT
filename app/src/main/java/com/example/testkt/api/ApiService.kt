//ApiService.kt

package com.example.testkt.api


import com.example.testkt.user.User
import retrofit2.http.GET


interface ApiService {
    @GET("users")
    suspend fun getUsers(): UserResponse
}

data class UserResponse (
    val data :List<User>?
)
