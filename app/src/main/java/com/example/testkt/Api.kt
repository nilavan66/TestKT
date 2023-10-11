package com.example.testkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testkt.api.CoroutineModelClass
import com.example.testkt.api.RetrofitInterface
import com.example.testkt.user.UserAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class Api : AppCompatActivity() {


    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var coroutineModel: CoroutineModelClass


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        userAdapter = UserAdapter(emptyList())
        coroutineModel = ViewModelProvider(this)[CoroutineModelClass::class.java]
        recyclerView = findViewById(R.id.userList)


        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        coroutineModel.getUserAdapter().observe(this) { userAdapter ->
            recyclerView.adapter = userAdapter
        }


    }

    private fun fetchData() {
        coroutineModel.fetchData()
    }
}