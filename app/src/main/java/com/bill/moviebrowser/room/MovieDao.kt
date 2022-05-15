package com.bill.moviebrowser.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
  @Query("SELECT * FROM movie ORDER BY id")
  fun getAll(): LiveData<List<Movie>>

  @Insert
  fun add(movie: Movie)

  @Insert
  fun add(vararg movie: Movie)

  @Insert
  fun add(movieList: List<Movie>)

  @Delete
  fun remove(movie: Movie)

  @Query("SELECT * FROM movie WHERE title LIKE :searchQuery")
  fun searchMovie(searchQuery: String): LiveData<List<Movie>>
}