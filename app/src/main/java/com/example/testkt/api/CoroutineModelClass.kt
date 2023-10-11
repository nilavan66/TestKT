package com.example.testkt.api


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testkt.user.UserAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineModelClass : ViewModel() {
    private var userAdapter = MutableLiveData<UserAdapter>()
    fun fetchData() {
viewModelScope.launch(Dispatchers.IO){
    try {
        val userResponse = RetrofitInterface.retrofit.getUsers()
        val users = userResponse.data ?: emptyList()
        withContext(Dispatchers.Main) {
            userAdapter.postValue(UserAdapter(users))


        }
    } catch (e: Exception) {
        // Handle exceptions here
        //Toast.makeText(applicationContext, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}
    }
    fun getUserAdapter() = userAdapter
}