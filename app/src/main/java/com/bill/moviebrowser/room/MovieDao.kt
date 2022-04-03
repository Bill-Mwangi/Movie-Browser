package com.bill.moviebrowser.room
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Insert
//import androidx.room.Query
//
//@Dao
//interface MovieDao {
//  @Query("SELECT * FROM movie")
//  fun getAll(): LiveData<List<Movie>>
//
//  @Insert
//  fun add(movie: Movie)
//
//  @Insert
//  fun add(vararg movie: Movie)
//
//  @Insert
//  fun add(movieList: List<Movie>)
//
//  @Delete
//  fun remove(movie: Movie)
//}