package com.example.testkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testkt.api.ApiService
import com.example.testkt.api.UserResponse
import com.example.testkt.user.UserAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

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
            .create(ApiService::class.java)

        //val apiService = retrofit.create(ApiService::class.java)
        //val call = apiService.getUsers()

        //val retrofit_data = retrofit.getUsers()

        /*
        retrofit_data.enqueue(object :Callback<UserResponse>{

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

*/
        /*

        try {
            val response = retrofit_data.execute()
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    // Print the response body to the console for debugging
                    println("Response Body: $responseBody")
                    val users = responseBody.data ?: emptyList()
                    userAdapter = UserAdapter(users)
                    recyclerView.adapter = userAdapter
                } else {
                    Log.e("response", "fetchData: null", )
                }
            } else {
                // Handle the error here
                // You can access the error code and message from response.errorBody() if needed.
            }
        }
            catch (e:Exception){
            Log.e("exception", "fetchData: ${e}")
            Toast.makeText(applicationContext, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
*/


        // CoroutineScope
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userResponse = retrofit.getUsers()
                val users = userResponse.data ?: emptyList()
                //update UI components
                withContext(Dispatchers.Main) {
                    userAdapter = UserAdapter(users)
                    recyclerView.adapter = userAdapter
                }
            } catch (e: Exception) {
                // Handle exceptions here
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Error: ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }
}