package com.example.scogo_task.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.scogo_task.R
import com.example.scogo_task.model.CryptoData

class CryptoListViewAdapter(private var cryptoList: List<CryptoData>) : RecyclerView.Adapter<CryptoListViewAdapter.CryptoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_crypto, parent, false)
        return CryptoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val crypto = cryptoList[position]
        holder.bind(crypto)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    fun updateData(newList: List<CryptoData>) {
        cryptoList = newList
        notifyDataSetChanged()
    }

    inner class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(crypto: CryptoData) {
            itemView.apply {
                // Binding the crypto data to the views
                findViewById<TextView>(R.id.cryptoName).text = crypto.name
                findViewById<TextView>(R.id.cryptoSymbol).text = crypto.symbol
                findViewById<TextView>(R.id.cryptoPrice).text = crypto.rank.toString() ?: "N/A"

                // Set an onClickListener to navigate to CryptoDetailActivity
                setOnClickListener {
                    val intent = Intent(context, CryptoDetailActivity::class.java)
                    intent.putExtra("crypto_id", crypto.id)
                    intent.putExtra("crypto_name", crypto.name)
                    intent.putExtra("crypto_symbol", crypto.symbol)
                    intent.putExtra("crypto_rank", crypto.rank)
                    context.startActivity(intent)
                }
            }
        }
    }
}
