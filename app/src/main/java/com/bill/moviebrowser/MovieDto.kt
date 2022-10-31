package com.bill.moviebrowser

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "movie")
data class MovieDto(
  @field:Json(name = "adult")
  val adult: Boolean,
  @field:Json(name = "id")
  @PrimaryKey
  @ColumnInfo(name = "movie_id")
  val movieId: Int,
  @field:Json(name = "title")
  val title: String,
  @field:Json(name = "overview")
  val description: String,
  @field:Json(name = "poster_path")
  val poster: String,
  @field:Json(name = "backdrop_path")
  val backdrop: String
) : Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.readBoolean(),
    parcel.readInt(),
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!
  )

  override fun describeContents(): Int {
    return 0
  }

  override fun writeToParcel(dest: Parcel?, flags: Int) {
    dest?.apply {
      this.writeBoolean(adult)
      this.writeInt(movieId)
      this.writeString(title)
      this.writeString(description)
      this.writeString(poster)
      this.writeString(backdrop)
    }
  }

  companion object CREATOR : Parcelable.Creator<MovieDto> {
    override fun createFromParcel(parcel: Parcel): MovieDto {
      return MovieDto(parcel)
    }

    override fun newArray(size: Int): Array<MovieDto?> {
      return arrayOfNulls(size)
    }
  }
}