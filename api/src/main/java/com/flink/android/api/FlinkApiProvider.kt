package com.flink.android.api

import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object FlinkApiProvider {

  fun createApiClient(): ApiClient = ApiClientImpl(createFlinkApi(), Dispatchers.IO)

  private fun createFlinkApi(): FlinkService {
    return Retrofit.Builder()
      .baseUrl("https://run.mocky.io")
      .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
      .build()
      .create(FlinkService::class.java)
  }
}