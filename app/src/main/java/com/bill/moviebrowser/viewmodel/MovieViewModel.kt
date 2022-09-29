package com.bill.moviebrowser.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bill.moviebrowser.CastDto
import com.bill.moviebrowser.MovieDto
import com.bill.moviebrowser.room.MovieCast
import com.bill.moviebrowser.room.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
//class MovieViewModel(application: Application) : AndroidViewModel(application) {
class MovieViewModel @Inject constructor(
  private val repository: MovieRepository,
  application: Application
) : AndroidViewModel(application) {
  private var _localData: MutableLiveData<List<MovieDto>> = MutableLiveData()
  private var _onlineData: MutableLiveData<List<MovieDto>> = MutableLiveData()
  private var _recommendations: MutableLiveData<List<MovieDto>> = MutableLiveData()
  private var _searchList: MutableLiveData<List<MovieDto>> = MutableLiveData()
  private var _castList: MutableLiveData<List<CastDto>> = MutableLiveData()

  val localData: LiveData<List<MovieDto>>
    get() = _localData

  val onlineData: LiveData<List<MovieDto>>
    get() = _onlineData

  val recommendations: LiveData<List<MovieDto>>
    get() = _recommendations

  val searchList: LiveData<List<MovieDto>>
    get() = _searchList

  val castList: LiveData<List<CastDto>>
    get() = _castList

  fun fetchData() {
    viewModelScope.launch(Dispatchers.IO) {
      _onlineData.postValue(repository.fetchPopularMovies())
//      _localData.postValue(repository.fetchLocalData())

      onlineData.value?.let {
        repository.addMovie(it)
      }
    }
  }

  fun searchDatabase(searchQuery: String) {
    viewModelScope.launch(Dispatchers.IO) {
      _searchList.postValue(repository.findMovie(searchQuery))
    }
  }

  fun data(movieId: Int) {
    viewModelScope.launch(Dispatchers.IO) {
      _recommendations.postValue(repository.fetchMovieRecommendations(movieId))
      val castList = repository.fetchMovieCast(movieId)
      _castList.postValue(castList.castList)
      repository.addCast(castList.castList)

      for (cast in castList.castList)
        repository.addMovieCast(MovieCast(movieId, cast.id))
    }
  }
}