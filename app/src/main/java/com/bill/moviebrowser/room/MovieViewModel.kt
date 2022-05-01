package com.bill.moviebrowser.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bill.moviebrowser.TVDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MovieViewModel(application: Application) : AndroidViewModel(application) {

  var localData: LiveData<List<Movie>> = MutableLiveData()

  //  var onlineData: MutableLiveData<List<Movie>> = MutableLiveData()
  private var repository: MovieRepository

  init {
    val movieDao = MovieDatabase.getDatabase(application).movieDao()
    repository = MovieRepository(movieDao, TVDB.getInstance())

    viewModelScope.launch(Dispatchers.IO) {
//      onlineData = repository.fetchOnlineData()
      localData = repository.fetchLocalData()
//
//      //FIXME: Temporarily adding remote data to local db
////      onlineData.value?.let {
////        repository.add(it)
////        Log.d("Local Data", "Data added")
////      }
    }

  }
}