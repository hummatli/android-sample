package com.flink.android.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Price(
  @Json(name = "amount")
  val amount: Double,

  @Json(name = "currency")
  val currency: String
)