package com.flink.android.challenge.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flink.android.api.Product

class ProductAdapter(
    private val callMinus: (product: Product) -> Unit,
    private val callPlus: (product: Product) -> Unit
) : RecyclerView.Adapter<ProductViewHolder>() {
    private var products: List<Product> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductViewHolder(parent, callMinus = callMinus, callPlus = callPlus)

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.render(products[position])

    override fun getItemCount() = products.size

    fun setData(products: List<Product>) {
        this.products = products
    }
}