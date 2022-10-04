package com.bill.moviebrowser.room
import com.bill.moviebrowser.CastDto
import com.bill.moviebrowser.MovieApi
import com.bill.moviebrowser.MovieDto
import javax.inject.Inject

class MovieRepository @Inject constructor(
  private val movieApi: MovieApi,
  private val movieDao: MovieDao
) {
  // Local Database
  fun getMovies(): List<MovieDto> = movieDao.getAllMovies()

  fun addMovie(vararg movie: MovieDto) = movieDao.addMovie(*movie)

  fun addMovie(movieList: List<MovieDto>) = movieDao.addMovie(movieList)

  fun findMovie(searchQuery: String): List<MovieDto> {
    return movieDao.searchMovie(searchQuery)
  }

  fun getCast(movieId: Int) = movieDao.getCast(movieId)

  fun addCast(vararg castListDto: CastDto) = movieDao.addCast(*castListDto)

  fun addCast(castList: List<CastDto>) = movieDao.addCast(castList)

  fun addMovieCast(vararg list: MovieCast) = movieDao.addMovieCast(*list)

  fun addMovieCast(list: List<MovieCast>) = movieDao.addMovieCast(list)

  // Online Sources
  suspend fun fetchPopularMovies(): List<MovieDto> =
    movieApi.getPopularMovies().movies
  //todo: Store secret keys

  suspend fun fetchMovieRecommendations(movieId: Int) =
    movieApi.getRecommendations(movieId).movies

  suspend fun fetchLatestMovie() = movieApi.getLatestMovie()

  suspend fun fetchMovieCast(movieId: Int) = movieApi.getCast(movieId).castList

  suspend fun fetchTopRatedMovies(): List<MovieDto> = movieApi.getTopRatedMovies().movies
}