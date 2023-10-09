package com.example.testkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testkt.api.ApiService
import com.example.testkt.api.UserResponse
import com.example.testkt.user.UserAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api : AppCompatActivity() {


    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        userAdapter = UserAdapter(emptyList())

        recyclerView = findViewById(R.id.userList)


        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = userAdapter

        fetchData()
    }

    private fun fetchData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.getUsers()

        call.enqueue(object :Callback<UserResponse>{

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful){
                    val users = response.body()?.data?: emptyList()
                    userAdapter = UserAdapter(users)
                    recyclerView.adapter = userAdapter
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
            }
        })




    }
}