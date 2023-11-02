package com.muammarahlnn.feature.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muammarahlnn.urflix.core.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file ProfileViewModel, 02/11/2023 18.28 by Muammar Ahlan Abimanyu
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
) : ViewModel() {

    var fullName by mutableStateOf("")
        private set

    fun setFullNameValue(fullName: String) {
        this.fullName = fullName
    }

    var email by mutableStateOf("")
        private set

    fun setEmailValue(email: String) {
        this.email = email
    }

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            profileRepository.getUser().collect { user ->
                fullName = user.fullName
                email = user.email
            }
        }
    }

    fun saveUser() {
        val saveUserJob = viewModelScope.launch {
            profileRepository.saveUser(fullName, email)
        }
        if (saveUserJob.isCompleted) {
            getUser()
        }
    }
}