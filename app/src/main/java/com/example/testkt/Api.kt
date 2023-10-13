package com.example.testkt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testkt.api.CoroutineModelClass
import com.example.testkt.api.RetrofitInt
import com.example.testkt.api.UserRepository
import com.example.testkt.user.UserAdapter

class Api : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var coroutineModel: CoroutineModelClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        userAdapter = UserAdapter(emptyList())

        val apiService = RetrofitInt.getInstance().retrofit
        val userRepository = UserRepository(apiService)
        coroutineModel = CoroutineModelClass(userRepository)
        recyclerView = findViewById(R.id.userList)


        recyclerView.layoutManager = LinearLayoutManager(this)
        coroutineModel.fetchData()
        coroutineModel.getUserAdapter().observe(this) { userAdapter ->
            recyclerView.adapter = userAdapter
        }


    }
}