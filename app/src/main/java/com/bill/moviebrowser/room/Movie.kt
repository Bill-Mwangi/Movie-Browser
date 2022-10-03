//package com.bill.moviebrowser.room
//
//import android.os.Parcel
//import android.os.Parcelable
//import androidx.room.*
//
//@Entity(tableName = "movie")
//data class Movie(
//  val tvdbId: Int = 0,
//  val title: String,
//  val description: String,
//  val poster: String,
//  val backdrop: String?
//) : Parcelable {
//  @PrimaryKey(autoGenerate = true)
//  @ColumnInfo(name = "id")
//  var movieId: Int = 0
//
//  constructor(parcel: Parcel) : this(
//    parcel.readInt(),
//    parcel.readString()!!,
//    parcel.readString()!!,
//    parcel.readString()!!,
//    parcel.readString()
//  ) {
//    movieId = parcel.readInt()
//  }
//
////  @Entity(tableName = "cast")
////  data class Cast(
////    @PrimaryKey val castId: Int,
////    val adult: Boolean,
////    val gender: Int,
////    val name: String,
////    val profilePath: String,
////    val character: String,
////    val order: Int
////  )
//
//  @Entity(tableName = "movie_cast", primaryKeys = ["tvdbId", "castId"])
//  data class MovieCastCrossRef(
//    val tvdbId: Int,
//    @ColumnInfo(index = true)
//    val castId: Int
//  )
//
//  data class MovieWithCast(
//    @Embedded val movie: Movie,
//    @Relation(
//      parentColumn = "tvdbId",
//      entityColumn = "castId",
//      associateBy = Junction(MovieCastCrossRef::class)
//    )
//    val cast: List<Cast>
//  )
//
//  data class CastWithMovie(
//    @Embedded val cast: Cast,
//    @Relation(
//      parentColumn = "castId",
//      entityColumn = "tvdbId",
//      associateBy = Junction(MovieCastCrossRef::class)
//    )
//    val movies: List<Movie>
//  )
//
//  override fun describeContents(): Int {
//    return 0
//  }
//
//  override fun writeToParcel(dest: Parcel?, flags: Int) {
//    if (dest != null) {
//      dest.writeInt(tvdbId)
//      dest.writeString(title)
//      dest.writeString(description)
//      dest.writeString(poster)
//      dest.writeString(backdrop)
//      dest.writeInt(movieId)
//    }
//  }
//
//  companion object CREATOR : Parcelable.Creator<Movie> {
//    override fun createFromParcel(parcel: Parcel): Movie {
//      return Movie(parcel)
//    }
//
//    override fun newArray(size: Int): Array<Movie?> {
//      return arrayOfNulls(size)
//    }
//  }
//}
