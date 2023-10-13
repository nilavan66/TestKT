package com.example.testkt.api

class UserRepository (private val apiService: ApiService){
    suspend fun getUsers() = apiService.getUsers()
}