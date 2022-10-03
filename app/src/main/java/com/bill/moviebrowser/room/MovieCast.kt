package com.bill.moviebrowser.room

import androidx.room.*
import com.bill.moviebrowser.CastDto
import com.bill.moviebrowser.MovieDto

@Entity(
  tableName = "movie_cast", primaryKeys = ["movie_id", "cast_id"], foreignKeys = [ForeignKey(
    entity = MovieDto::class,
    parentColumns = arrayOf("movie_id"),
    childColumns = arrayOf("movie_id"),
    onUpdate = ForeignKey.CASCADE,
    onDelete = ForeignKey.CASCADE
  ), ForeignKey(
    entity = CastDto::class,
    parentColumns = arrayOf("cast_id"),
    childColumns = arrayOf("cast_id"),
    onUpdate = ForeignKey.CASCADE,
    onDelete = ForeignKey.CASCADE
  )], indices = [Index(value = ["movie_id", "cast_id"], unique = true)]
)
data class MovieCast(
  @ColumnInfo(name = "movie_id")
  val movieId: Int,
  @ColumnInfo(name = "cast_id")
  val castId: Int
)