package com.bill.moviebrowser

import com.squareup.moshi.Json

data class ConfigDto(
  @field:Json(name = "base_url")
  val baseUrl: String,
  @field:Json(name = "secure_base_url")
  val secureBaseUrl: String
)
