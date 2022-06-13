package com.bill.moviebrowser.room

import com.bill.moviebrowser.TVDB

class MovieRepository(private val movieDao: MovieDao, private val tvdb: TVDB) {

  fun fetchLocalData(): List<Movie> = movieDao.getAll()

  suspend fun fetchOnlineData(): List<Movie> =
    tvdb.getPopularMovies().movies

  suspend fun fetchMovieRecommendations(movieId: Int, pageNo: Int) =
    tvdb.getRecommendations(movieId, pageNo).movies

  suspend fun fetchMovieCast(movieId: Int) = tvdb.getCast(movieId).list

  fun add(movieList: List<Movie>) = movieDao.add(movieList)

  fun search(searchQuery: String): List<Movie> {
    return movieDao.searchMovie(searchQuery)
  }
}