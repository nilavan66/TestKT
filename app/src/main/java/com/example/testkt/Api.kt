//Api.kt

package com.example.testkt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testkt.api.CoroutineModelClass
import com.example.testkt.user.UserAdapter

class Api : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        userAdapter = UserAdapter(emptyList())

        recyclerView = findViewById(R.id.userList)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = userAdapter

        intViewModel()


    }

    private fun intViewModel() {
        val viewModel = ViewModelProvider(this)[CoroutineModelClass::class.java]
        viewModel.fetchData()
        viewModel.getUsersLiveData().observe(this) { users ->
            //initAdapter(users)
            userAdapter.updateData(users)
        }
    }

    /*
    private fun initAdapter(data: List<User>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        val updatedAdapter = UserAdapter(data)
        recyclerView.adapter = updatedAdapter
    }
    */
}