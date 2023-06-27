package org.bedu.proyecto01equipo16.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.bedu.proyecto01equipo16.login.data.UserRepository
import org.bedu.proyecto01equipo16.login.model.User

class LoginViewModel : ViewModel() {
    private val repo = UserRepository()

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    fun doLogin(email: String, password: String) {
        viewModelScope.launch {
            if(repo.loginUser(email, password)) {
                _user.value = repo.getUser(email)
            }else {
                _user.value = null
            }
        }
    }
}
