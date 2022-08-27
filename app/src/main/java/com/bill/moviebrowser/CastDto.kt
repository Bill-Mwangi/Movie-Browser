package com.bill.moviebrowser

import com.squareup.moshi.Json

data class CastDto(
  @field:Json(name = "adult")
  val adult: Boolean,
  @field:Json(name = "gender")
  val gender: Int,
  @field:Json(name = "name")
  val name: String,
  @field:Json(name = "character")
  val character: String,
  @field:Json(name = "profile_path")
  val profile: String
)
