package com.bill.moviebrowser.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bill.moviebrowser.CastDto
import com.bill.moviebrowser.MovieDto

@Dao
interface MovieDao {
  @Query("SELECT * FROM movie ORDER BY movie_id")
  fun getAllMovies(): List<MovieDto>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun addMovie(vararg movie: MovieDto)

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun addMovie(movieList: List<MovieDto>)

  @Query("SELECT * FROM movie WHERE title LIKE :searchQuery")
  fun searchMovie(searchQuery: String): List<MovieDto>

  @Insert
  fun insertMovieCast(vararg movieCast: MovieCast)

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun addCast(vararg cast: CastDto)

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun addCast(castList: List<CastDto>)

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun addMovieCast(vararg movieCast: MovieCast)

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun addMovieCast(movieCastList: List<MovieCast>)

  @Query("SELECT * FROM cast_table c inner join movie_cast mc on c.cast_id = mc.cast_id WHERE movie_id = :movieId")
  fun getCast(movieId: Int): List<CastDto>


}