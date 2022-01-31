package com.flink.android.api

import retrofit2.http.GET

internal interface FlinkService {

  @GET("/v3/541cf783-5f48-43ca-ac10-c9e90ed84207")
  suspend fun getProducts(): List<Product>

  @GET("/v3/65c2b822-251c-40bd-b38e-54645ae40f84")
  suspend fun getProductsEmpty(): List<Product>

  @GET("/v3/6b184db2-c57a-44cc-b694-0e2a411fdf8c")
  suspend fun getProductsError(): List<Product>
}