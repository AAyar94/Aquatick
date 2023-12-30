package com.aayar94.aquatick.ui.screen.login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    val client = FirebaseAuth.getInstance()


    fun logInWithEmail(email: String, password: String) {
        client.signInWithEmailAndPassword(email, password)
    }

}