package com.bill.moviebrowser.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
  entities = [Movie::class], version = 1, exportSchema = true
)
abstract class MovieDatabase : RoomDatabase() {

  abstract fun movieDao(): MovieDao

  companion object {
    @Volatile
    private var INSTANCE: MovieDatabase? = null

    fun getDatabase(context: Context): MovieDatabase {
      val tempInstance = INSTANCE
      if (tempInstance != null) return tempInstance

      synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          MovieDatabase::class.java,
          "movie_database"
        ).build()
        INSTANCE = instance
        return instance
      }
    }
  }
}