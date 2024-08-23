package com.example.scogo_task.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scogo_task.repositories.CryptoRepository
import com.example.scogo_task.model.CryptoData
import kotlinx.coroutines.launch

class CryptoViewModel(private val repository: CryptoRepository): ViewModel() {
    private val _cryptoData = MutableLiveData<List<CryptoData>?>()
    val cryptoData: MutableLiveData<List<CryptoData>?> = _cryptoData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchCryptoData() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _cryptoData.value = repository.getCryptoData()
            } catch (e: Exception) {
                _cryptoData.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchCryptoData(query: String): List<CryptoData>? {
        return _cryptoData.value?.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.symbol.contains(query, ignoreCase = true)
        }
    }
}