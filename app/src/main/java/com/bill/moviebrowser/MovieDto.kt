package com.bill.moviebrowser

import com.squareup.moshi.Json

data class MovieDto(
  @field:Json(name = "adult")
  val adult: Boolean,
  @field:Json(name = "id")
  val movieId: Int,
  @field:Json(name = "title")
  val title: String,
  @field:Json(name = "overview")
  val description: String,
  @field:Json(name = "poster_path")
  val poster: String,
  @field:Json(name = "backdrop_path")
  val backdrop: String,
  @field:Json(name = "runtime")
  val runtime: Int
)