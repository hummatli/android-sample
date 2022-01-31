package com.flink.android.challenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flink.android.api.Price
import com.flink.android.api.Product
import java.text.NumberFormat
import java.util.*
import com.flink.android.challenge.databinding.ViewholderProductBinding as Binding

class ProductViewHolder(
    parent: ViewGroup,
    private val binding: Binding = Binding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    ),
    private val callMinus: (product: Product) -> Unit,
    private val callPlus: (product: Product) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun render(product: Product) {
        with(binding) {
            imageView.load(product.thumbnail)
            labelTitle.text = product.title
            labelPrice.text = product.price.format()
            labelCount.text = product.value.toString()
            buttonMinus.setOnClickListener {
                callMinus.invoke(product)
            }
            buttonPlus.setOnClickListener {
                callPlus.invoke(product)
            }
        }
    }
}

private fun Price.format(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY)
    formatter.currency = Currency.getInstance(currency)
    return formatter.format(amount)
}