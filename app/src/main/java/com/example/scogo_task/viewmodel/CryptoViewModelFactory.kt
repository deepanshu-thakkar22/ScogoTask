package com.example.scogo_task.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.scogo_task.repositories.CryptoRepository

class CryptoViewModelFactory(private val repository: CryptoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CryptoViewModel::class.java)) {
            return CryptoViewModel(repository) as T
        }
        throw IllegalArgumentException("cannot find view model instance")
    }
}
