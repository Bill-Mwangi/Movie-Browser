package com.bill.moviebrowser.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class Movie(
  @SerializedName("id")
  @ColumnInfo(name = "tvdb_id")
  val tvdbID: Int = 0,
  @SerializedName("original_title")
  val title: String,
  @SerializedName("overview")
  val description: String,
  @SerializedName("poster_path")
  val imagePath: String
) {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  var movieID: Int = 0
}
