package com.bill.moviebrowser.room

import androidx.room.TypeConverter
import com.bill.moviebrowser.CastDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
  @TypeConverter
  fun fromMovieCast(castList: List<CastDto>): String {
    return Gson().toJson(castList)
  }

  @TypeConverter
  fun toMovieCast(castListString: String): List<CastDto> {
    return Gson().fromJson(castListString, listType<CastDto>())
  }

  private fun <T> listType(): Type {
    return object : TypeToken<List<T>>() {}.type
  }
}