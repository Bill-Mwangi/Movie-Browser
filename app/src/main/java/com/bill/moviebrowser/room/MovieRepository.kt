package com.bill.moviebrowser.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bill.moviebrowser.TVDB
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDao: MovieDao, private val tvdb: TVDB) {

  fun fetchLocalData(): LiveData<List<Movie>> = movieDao.getAll()

  suspend fun fetchOnlineData(): MutableLiveData<List<Movie>> =
    MutableLiveData(tvdb.getPopularMovies().movies)

  fun add(movieList: List<Movie>) = movieDao.add(movieList)

  fun search(searchQuery: String): Flow<List<Movie>> {
    return movieDao.searchMovie(searchQuery)
  }
}