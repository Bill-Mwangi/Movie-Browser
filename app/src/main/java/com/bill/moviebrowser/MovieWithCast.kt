package com.bill.moviebrowser

import com.squareup.moshi.Json

data class MovieWithCast(
  @field:Json(name = "id")
  val movieId: Int,
  @field:Json(name = "cast")
  val castList: List<CastDto>
)
