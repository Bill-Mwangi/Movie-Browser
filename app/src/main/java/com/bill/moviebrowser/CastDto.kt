package com.bill.moviebrowser

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Entity(
  tableName = "cast_table"
)
@Serializable
data class CastDto(
  @PrimaryKey
  @field:Json(name = "id")
  @ColumnInfo(name = "cast_id")
  val id: Int,
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
