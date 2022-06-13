package com.bill.moviebrowser.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bill.moviebrowser.TVDB
import com.bill.moviebrowser.room.Movie
import com.bill.moviebrowser.room.MovieDatabase
import com.bill.moviebrowser.room.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MovieViewModel(application: Application) : AndroidViewModel(application) {

  private var _localData: MutableLiveData<List<Movie>> = MutableLiveData()
  private var _onlineData: MutableLiveData<List<Movie>> = MutableLiveData()
  private var _recommendations: MutableLiveData<List<Movie>> = MutableLiveData()
  private var _searchList: MutableLiveData<List<Movie>> = MutableLiveData()
  private var _castList: MutableLiveData<List<TVDB.Cast>> = MutableLiveData()

  val localData: LiveData<List<Movie>>
    get() = _localData

  val onlineData: LiveData<List<Movie>>
    get() = _onlineData

  val recommendations: LiveData<List<Movie>>
    get() = _recommendations

  val searchList: LiveData<List<Movie>>
    get() = _searchList

  val castList: LiveData<List<TVDB.Cast>>
    get() = _castList

  private var repository: MovieRepository

  init {
    val movieDao = MovieDatabase.getDatabase(application).movieDao()
    repository = MovieRepository(movieDao, TVDB.getInstance())

    viewModelScope.launch(Dispatchers.IO) {
      _localData.postValue(repository.fetchLocalData())
      _onlineData.postValue(repository.fetchOnlineData())

//      onlineData.value?.let {
//        repository.add(it)
//      }
    }
  }

  fun searchDatabase(searchQuery: String) {
    viewModelScope.launch(Dispatchers.IO) {
      _searchList.postValue(repository.search(searchQuery))
    }

  }

  fun data(movieId: Int, pageNo: Int = 1) {
    viewModelScope.launch(Dispatchers.IO) {
      _recommendations.postValue(repository.fetchMovieRecommendations(movieId, pageNo))
      _castList.postValue(repository.fetchMovieCast(movieId))
    }
  }
}