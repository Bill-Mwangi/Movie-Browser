package com.bill.moviebrowser.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bill.moviebrowser.CastDto
import com.bill.moviebrowser.MovieDto
import com.bill.moviebrowser.room.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
  private val repository: MovieRepository,
  application: Application
) : AndroidViewModel(application) {
  private var _localData: MutableLiveData<List<MovieDto>> = MutableLiveData()
  private var _topRatedMovies: MutableLiveData<List<MovieDto>> = MutableLiveData()
  private var _popularMovies: MutableLiveData<List<MovieDto>> = MutableLiveData()
  private var _recommendations: MutableLiveData<List<MovieDto>> = MutableLiveData()
  private var _searchList: MutableLiveData<List<MovieDto>> = MutableLiveData()
  private var _castList: MutableLiveData<List<CastDto>> = MutableLiveData()
  private var _newest: MovieDto? = null

  val localData: LiveData<List<MovieDto>>
    get() = _localData

  val topRatedMovies: LiveData<List<MovieDto>>
    get() = _topRatedMovies

  val popularMovies: LiveData<List<MovieDto>>
    get() = _popularMovies

  val newest: MovieDto?
    get() = _newest

  val recommendations: LiveData<List<MovieDto>>
    get() = _recommendations

  val searchList: LiveData<List<MovieDto>>
    get() = _searchList

  val castList: LiveData<List<CastDto>>
    get() = _castList

  fun fetchData() {
    viewModelScope.launch(Dispatchers.IO) {
      try {
        _newest = repository.fetchLatestMovie()
        _topRatedMovies.postValue(repository.fetchTopRatedMovies())
        _popularMovies.postValue(repository.fetchPopularMovies())
        _localData.postValue(repository.getMovies())
      } catch (e: Exception) {
        Log.e("$e", "Exception in function fetchData()")
      } finally {
        updateLocalData()
      }
    }
  }

  private fun updateLocalData() {
    _topRatedMovies.value?.let {
      repository.addMovie(it)
    }
    _popularMovies.value?.let {
      repository.addMovie(it)
    }
    _newest?.let {
      repository.addMovie(it)
    }
  }

  fun searchDatabase(searchQuery: String) {
    viewModelScope.launch(Dispatchers.IO) {
      _searchList.postValue(repository.findMovie(searchQuery))
    }
  }

  fun data(movieId: Int) {
    fetchRecommendations(movieId)
    fetchCastandCrew(movieId)
  }

  private fun fetchRecommendations(movieId: Int) {
    viewModelScope.launch(Dispatchers.IO) {
      val recommendations = repository.fetchMovieRecommendations(movieId)
      _recommendations.postValue(recommendations)
      _recommendations.let {
      }
    }
  }

  private fun fetchCastandCrew(movieId: Int) {
    viewModelScope.launch(Dispatchers.IO) {
      val castList: List<CastDto> = repository.fetchMovieCast(movieId)
      _castList.postValue(castList)
      updateCast(movieId, castList)
    }
  }

  private fun updateCast(movieId: Int, castList: List<CastDto>) {
    viewModelScope.launch(Dispatchers.IO) {
      repository.addCast(castList)
//      for (cast in castList)
//        repository.addMovieCast(MovieCast(movieId, cast.id))
    }
  }

  fun flush() {
    _castList.postValue(emptyList())
    _recommendations.postValue(emptyList())
  }
}