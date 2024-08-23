package com.example.scogo_task.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scogo_task.databinding.ActivityCryptoListBinding
import com.example.scogo_task.model.CryptoData
import com.example.scogo_task.repositories.CryptoRepository
import com.example.scogo_task.viewmodel.CryptoViewModel
import com.example.scogo_task.viewmodel.CryptoViewModelFactory

class CryptoActivity : AppCompatActivity() {

    private lateinit var viewModel: CryptoViewModel
    private lateinit var cryptoListAdapter: CryptoListViewAdapter
    private lateinit var binding: ActivityCryptoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCryptoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = CryptoRepository()
        val factory = CryptoViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[CryptoViewModel::class.java]
        viewModel.fetchCryptoData()
        observeViewModel()
        setupSwipeRefreshLayout()

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let { filterData(it.toString()) }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

    }

    private fun observeViewModel() {
        viewModel.cryptoData.observe(this) { data ->
            if (data != null) {
                binding.loadingIndicator.visibility = View.GONE
                binding.errorMessage.visibility = View.GONE
                setupRecyclerView(data)
            } else {
                binding.loadingIndicator.visibility = View.GONE
                binding.errorMessage.visibility = View.VISIBLE
            }
        }
        viewModel.isLoading.observe(this) { isLoading ->
            binding.loadingIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchCryptoData()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setupRecyclerView(cryptoData : List<CryptoData>) {
        cryptoListAdapter = CryptoListViewAdapter(cryptoData)
        binding.cryptoRecyclerView.apply {
            adapter = cryptoListAdapter
            layoutManager = LinearLayoutManager(this@CryptoActivity)
        }
    }

    private fun filterData(query: String) {
        val filteredList = viewModel.searchCryptoData(query)
        if (filteredList.isNullOrEmpty()) {
            binding.cryptoRecyclerView.visibility = View.GONE
            binding.noResultsMessage.visibility = View.VISIBLE
        } else {
            binding.cryptoRecyclerView.visibility = View.VISIBLE
            binding.noResultsMessage.visibility = View.GONE
            cryptoListAdapter.updateData(filteredList)
        }
    }
}
