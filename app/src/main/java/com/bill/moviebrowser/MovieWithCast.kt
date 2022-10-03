package com.bill.moviebrowser

import com.squareup.moshi.Json

data class MovieWithCast(
  @field:Json(name = "id")
  val movieId: Int,
  @field:Json(name = "cast_list")
  val castList: List<CastDto>
)
