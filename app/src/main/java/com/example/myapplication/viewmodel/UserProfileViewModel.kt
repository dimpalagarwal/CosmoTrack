// UserProfileViewModel.kt
package com.example.myapplication.viewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserProfileViewModel : ViewModel() {
    var profileImageUri = mutableStateOf<Uri?>(null)
    var name = mutableStateOf<String?>(null)

    fun setProfileImage(uri: Uri) {
        profileImageUri.value = uri
    }
}



