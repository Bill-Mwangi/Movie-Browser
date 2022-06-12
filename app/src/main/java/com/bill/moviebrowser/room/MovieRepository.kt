package com.bill.moviebrowser.room

import com.bill.moviebrowser.TVDB

class MovieRepository(private val movieDao: MovieDao, private val tvdb: TVDB) {

  fun fetchLocalData(): List<Movie> = movieDao.getAll()

  fun fetchOnlineData(): List<Movie> =
    tvdb.getPopularMovies().movies

  fun fetchRecommendations(movieId: Int, pageNo: Int) =
    tvdb.getRecommendations(movieId, pageNo).movies

  fun add(movieList: List<Movie>) = movieDao.add(movieList)

  fun search(searchQuery: String): List<Movie> {
    return movieDao.searchMovie(searchQuery)
  }
}