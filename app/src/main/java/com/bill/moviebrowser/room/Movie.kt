package com.bill.moviebrowser.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class Movie(
  @SerializedName("id")
  val tvdbId: Int = 0,
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
  var movieId: Int = 0

  constructor(parcel: Parcel) : this(
    parcel.readInt(),
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()
  ) {
    movieId = parcel.readInt()
  }

  @Entity(tableName = "cast")
  data class Cast(
    @SerializedName("id")
    @PrimaryKey val castId: Int,
    val adult: Boolean,
    val gender: Int,
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String,
    val character: String,
    val order: Int
  )

  @Entity(tableName = "movie_cast", primaryKeys = ["tvdbId", "castId"])
  data class MovieCastCrossRef(
    val tvdbId: Int,
    @ColumnInfo(index = true)
    val castId: Int
  )

  data class MovieWithCast(
    @Embedded val movie: Movie,
    @Relation(
      parentColumn = "tvdbId",
      entityColumn = "castId",
      associateBy = Junction(MovieCastCrossRef::class)
    )
    val cast: List<Cast>
  )

  data class CastWithMovie(
    @Embedded val cast: Cast,
    @Relation(
      parentColumn = "castId",
      entityColumn = "tvdbId",
      associateBy = Junction(MovieCastCrossRef::class)
    )
    val movies: List<Movie>
  )

  override fun describeContents(): Int {
    return 0
  }

  override fun writeToParcel(dest: Parcel?, flags: Int) {
    if (dest != null) {
      dest.writeInt(tvdbId)
      dest.writeString(title)
      dest.writeString(description)
      dest.writeString(poster)
      dest.writeString(backdrop)
      dest.writeInt(movieId)
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
