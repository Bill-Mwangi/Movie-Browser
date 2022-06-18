package com.bill.moviebrowser.room

import androidx.room.*

@Dao
interface MovieDao {
  @Query("SELECT * FROM movie ORDER BY id")
  fun getAll(): List<Movie>

  @Insert
  fun add(movie: Movie)

  @Insert
  fun add(vararg movie: Movie)

  @Insert
  fun add(movieList: List<Movie>)

  @Delete
  fun remove(movie: Movie)

  @Query("SELECT * FROM movie WHERE title LIKE :searchQuery")
  fun searchMovie(searchQuery: String): List<Movie>

  @Transaction
  @Query("SELECT * FROM movie")
  fun getMovieWithCast(): Movie.MovieWithCast

  @Transaction
  @Query("SELECT * FROM cast")
  fun getCastWithMovies(): Movie.CastWithMovie
}