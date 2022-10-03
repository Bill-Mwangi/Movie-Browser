package com.bill.moviebrowser.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bill.moviebrowser.CastDto
import com.bill.moviebrowser.MovieDto

@Database(
  entities = [MovieDto::class, CastDto::class, MovieCast::class],
  version = 1,
  exportSchema = false,
//  autoMigrations = [AutoMigration(from = 1, to = 2)]
)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
  abstract fun movieDao(): MovieDao
}