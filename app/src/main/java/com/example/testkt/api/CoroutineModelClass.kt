//CoroutineModelClass.kt

package com.example.testkt.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testkt.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineModelClass : ViewModel() {
    private val userRepository: UserRepository = UserRepository(RetrofitInt.getInstance())
    private var usersLiveData = MutableLiveData<List<User>>()

    fun fetchData() {

        viewModelScope.launch(Dispatchers.IO) {
            try {

                val userResponse = userRepository.getUsers()
                val users = userResponse.data ?: emptyList()

                withContext(Dispatchers.Main) {
                    usersLiveData.value = users
                }

            } catch (e: Exception) {
                // Handle exceptions here
                //Toast.makeText(applicationContext, "Error: ${e.message}", Toast.LENGTH_SHORT).show()

            }
        }
    }

    fun getUsersLiveData(): LiveData<List<User>> = usersLiveData
}