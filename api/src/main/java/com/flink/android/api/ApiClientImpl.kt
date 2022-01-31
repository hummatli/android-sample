package com.flink.android.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class ApiClientImpl(
  private val service: FlinkService,
  private val dispatcher: CoroutineDispatcher
) : ApiClient {

  override suspend fun getProducts(): List<Product> = withContext(dispatcher) {
    return@withContext service.getProducts()
  }

  override suspend fun getProductsEmpty(): List<Product> = withContext(dispatcher) {
    return@withContext service.getProductsEmpty()
  }

  override suspend fun getProductsError(): List<Product> = withContext(dispatcher) {
    return@withContext service.getProductsError()
  }
}