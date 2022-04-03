package com.bill.moviebrowser.room

import android.graphics.Bitmap
//import android.util.Log
//import android.view.View
//import androidx.room.Entity
//import androidx.room.Ignore
//import androidx.room.OnConflictStrategy
//import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//@Entity(tableName = "movie")
data class Movie(
//  @PrimaryKey(OnConflict = OnConflictStrategy.REPLACE)
  val id: Int,
  @SerializedName("original_title")
  val title: String,
  @SerializedName("overview")
  val description: String,
  @SerializedName("poster_path")
  val imagePath: String,
//  @Ignore
  var image: Bitmap?
)
