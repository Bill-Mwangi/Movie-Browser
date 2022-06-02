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

  var localData: LiveData<List<Movie>> = MutableLiveData()

  var onlineData: MutableLiveData<List<Movie>> = MutableLiveData()
  private var repository: MovieRepository

  init {
    val movieDao = MovieDatabase.getDatabase(application).movieDao()
    repository = MovieRepository(movieDao, TVDB.getInstance())

    viewModelScope.launch(Dispatchers.IO) {
      localData = repository.fetchLocalData()
      onlineData = repository.fetchOnlineData()

      onlineData.value?.let {
        repository.add(it)
      }
    }
  }

  fun searchDatabase(searchQuery: String): LiveData<List<Movie>> {
    return repository.search(searchQuery)
  }
}