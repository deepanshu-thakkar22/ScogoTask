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

        val cryptoname = intent.getStringExtra("crypto_name")
        val cryptosymbol = intent.getStringExtra("crypto_symbol")
        val cryptorank = intent.getStringExtra("crypto_rank")

            binding.cryptoNameDetail.text = cryptoname
            binding.cryptoSymbolDetail.text = cryptosymbol
            binding.cryptoRankDetail.text = cryptorank
    }

}
