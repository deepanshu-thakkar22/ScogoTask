package com.example.scogo_task.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.scogo_task.databinding.ActivityCryptoDetailsBinding

class CryptoDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCryptoDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCryptoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cryptoName = intent.getStringExtra("crypto_name")
        val cryptoSymbol = intent.getStringExtra("crypto_symbol")
        val cryptoRank = intent.getStringExtra("crypto_rank")

            binding.cryptoNameDetail.text = cryptoName
            binding.cryptoSymbolDetail.text = cryptoSymbol
            binding.cryptoRankDetail.text = cryptoRank
    }

}
