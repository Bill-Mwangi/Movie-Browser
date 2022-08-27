package com.bill.moviebrowser

import com.squareup.moshi.Json

data class PageDto(
  @field:Json(name = "page")
  val page: Int,
  @field:Json(name = "results")
  val movies: List<MovieDto>
)
