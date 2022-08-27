package com.bill.moviebrowser.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
  entities = [Movie::class],
  version = 2,
  exportSchema = true,
  autoMigrations = [AutoMigration(from = 1, to = 2)]
)
abstract class MovieDatabase : RoomDatabase() {
  abstract fun movieDao(): MovieDao
}