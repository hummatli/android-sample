package com.flink.android.api

interface ApiClient {

  suspend fun getProducts(): List<Product>

  suspend fun getProductsEmpty(): List<Product>

  suspend fun getProductsError(): List<Product>
}