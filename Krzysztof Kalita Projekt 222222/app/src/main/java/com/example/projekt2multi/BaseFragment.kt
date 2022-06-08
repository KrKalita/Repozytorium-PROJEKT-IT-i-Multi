package com.example.projekt2multi

import android.content.Intent
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    protected fun startApp(){
        val intent = Intent(requireContext(), MainActivity2::class.java).apply {
            flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        startActivity(intent)
    }
}