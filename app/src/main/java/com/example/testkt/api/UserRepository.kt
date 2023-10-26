//UserRepository.kt
package com.example.testkt.api

class UserRepository (private val retrofitInt: RetrofitInt){
    suspend fun getUsers() = retrofitInt.retrofit.getUsers()
}