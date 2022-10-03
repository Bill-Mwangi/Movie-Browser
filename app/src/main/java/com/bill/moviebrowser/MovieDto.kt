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
    parcel.readByte() != 0.toByte(),
    parcel.readInt(),
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!
  )

  /**
   * Describe the kinds of special objects contained in this Parcelable
   * instance's marshaled representation. For example, if the object will
   * include a file descriptor in the output of [.writeToParcel],
   * the return value of this method must include the
   * [.CONTENTS_FILE_DESCRIPTOR] bit.
   *
   * @return a bitmask indicating the set of special object types marshaled
   * by this Parcelable object instance.
   */
  override fun describeContents(): Int {
    return 0
  }

  /**
   * Flatten this object in to a Parcel.
   *
   * @param dest The Parcel in which the object should be written.
   * @param flags Additional flags about how the object should be written.
   * May be 0 or [.PARCELABLE_WRITE_RETURN_VALUE].
   */
  override fun writeToParcel(dest: Parcel?, flags: Int) {
    if (dest != null) {
      dest.writeInt(movieId)
      dest.writeString(title)
      dest.writeString(description)
      dest.writeString(poster)
      dest.writeString(backdrop)
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