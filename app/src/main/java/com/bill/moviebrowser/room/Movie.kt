package com.bill.moviebrowser.room

import android.os.Parcel
import android.os.Parcelable
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
  val poster: String,
  @SerializedName("backdrop_path")
  val backdrop: String?
) : Parcelable {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  var movieID: Int = 0

  constructor(parcel: Parcel) : this(
    parcel.readInt(),
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()
  ) {
    movieID = parcel.readInt()
  }

  override fun describeContents(): Int {
    return 0
  }

  override fun writeToParcel(dest: Parcel?, flags: Int) {
    if (dest != null) {
      dest.writeInt(tvdbID)
      dest.writeString(title)
      dest.writeString(description)
      dest.writeString(poster)
      dest.writeString(backdrop)
      dest.writeInt(movieID)
    }
  }

  companion object CREATOR : Parcelable.Creator<Movie> {
    override fun createFromParcel(parcel: Parcel): Movie {
      return Movie(parcel)
    }

    override fun newArray(size: Int): Array<Movie?> {
      return arrayOfNulls(size)
    }
  }
}
