package com.bill.moviebrowser.room

//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import com.bill.moviebrowser.TVDB
//
//class MovieViewModel(application: Application): AndroidViewModel(application) {
//
//  var readAllData: LiveData<List<Movie>>
//  private var repository: MovieRepository
//
//  init {
//    val movieDao = MovieDatabase.getDatabase(application).movieDao()
//    repository = MovieRepository(movieDao, TVDB.getInstance())
//    readAllData = repository.readLocalData()
//  }
//}