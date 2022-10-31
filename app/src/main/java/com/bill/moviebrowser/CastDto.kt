package com.bill.moviebrowser

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
  tableName = "cast_table"
)
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
) : Parcelable {

  constructor(parcel: Parcel) : this(
    parcel.readInt(),
    parcel.readBoolean(),
    parcel.readInt(),
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!
  )

  override fun describeContents(): Int {
    return 0
  }

  override fun writeToParcel(dest: Parcel?, flags: Int) {
    dest?.apply {
      this.writeInt(id)
      this.writeBoolean(adult)
      this.writeInt(gender)
      this.writeString(name)
      this.writeString(character)
      this.writeString(profile)
    }
  }

  companion object CREATOR : Parcelable.Creator<CastDto> {
    override fun createFromParcel(parcel: Parcel): CastDto {
      return CastDto(parcel)
    }

    override fun newArray(size: Int): Array<CastDto?> {
      return arrayOfNulls(size)
    }
  }
}
