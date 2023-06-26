package org.bedu.proyecto01equipo16.login.signup

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.bedu.proyecto01equipo16.login.data.UserRepository
import org.bedu.proyecto01equipo16.utils.PermissionManager

class RegisterViewModel : ViewModel() {
    private val repo = UserRepository()

    private val _emptyFields = MutableLiveData<Boolean>()
    val emptyFields: LiveData<Boolean> = _emptyFields

    private val _passwordBadFormat = MutableLiveData<Boolean>()
    val passwordBadFormat: LiveData<Boolean> = _passwordBadFormat

    private val _successfulCreated = MutableLiveData<Boolean>()
    val successfulCreated: LiveData<Boolean> = _successfulCreated

    private val _setProfilePicture = MutableLiveData<Boolean>()
    val setProfilePicture: LiveData<Boolean> = _setProfilePicture

    private val _launchCamera = MutableLiveData<Boolean>()
    val launchCamera: LiveData<Boolean> = _launchCamera

    fun validateDateAndSave(name: String, phoneNumber: String, email: String, password: String, confirmPassword: String, profilePicture: String) {
        if(name.isNotEmpty() && phoneNumber.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
            if (password.length < 6 || password != confirmPassword) {
                _passwordBadFormat.value = true
                return
            }
            _passwordBadFormat.value = false
            _emptyFields.value = false
            registerUser(name, phoneNumber, email, password, profilePicture)
        } else {
            _emptyFields.value = true
            return
        }
    }

    private fun registerUser(
        name: String,
        phoneNumber: String,
        email: String,
        password: String,
        profilePicture: String
    ) {
        viewModelScope.launch {
            _successfulCreated.value = repo.newUser(name, phoneNumber, email, password, profilePicture)
        }
    }

    fun requestCamera(context: Context, activity: Activity) {
        when {
            !PermissionManager.checkStoragePermission(context) -> {
                PermissionManager.requestStoragePermission(activity)
                return
            }
            !PermissionManager.checkCameraPermission(context) -> {
                PermissionManager.requestCameraPermission(activity)
                return
            }
            else -> _launchCamera.value = true
        }
    }

    fun onRequestPermissionsResult(
        requestCode: Int, activity: Activity
    ) {
        when (requestCode) {
            PermissionManager.STORAGE_PERMISSION_CODE -> {
                PermissionManager.requestCameraPermission(activity)
                return
            }
            PermissionManager.CAMERA_PERMISSION_CODE -> {
                _launchCamera.value = true
                return
            }
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        _launchCamera.value = false
        _setProfilePicture.value = false

        if (requestCode == PermissionManager.CAMERA_RESULT_CODE) {
            _setProfilePicture.value = true
            return
        }
    }
}
