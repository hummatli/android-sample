package com.flink.android.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
  @Json(name = "id")
  val id: String,

  @Json(name = "name")
  val title: String,

  @Json(name = "thumbnail")
  val thumbnail: String,

  @Json(name = "price")
  val price: Price,

  val value: Int = 0
)