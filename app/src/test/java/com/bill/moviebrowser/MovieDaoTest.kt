package com.bill.moviebrowser

import com.bill.moviebrowser.room.MovieDao
import org.junit.Assert.assertEquals
import org.junit.Test
import javax.inject.Inject

class MovieDaoTest {
  @Inject
  private lateinit var movieDao: MovieDao

  private val movie = MovieDto(
    false,
    851238,
    "Guglhupfgeschwader",
    "Bavaria's most relaxed village policeman has to face an organized crime syndicate coming after his grandmother's cakes.",
    "/5KAMzcS4jWn8E9kOY4QfCeJ33hs.jpg",
    "/AiR95OFlkY00Z18uLl2eL2pZNeL.jpg"
  )

  @Test
  fun addMovie() {
    movieDao.addMovie(movie)
    assertEquals(movie, movieDao.searchMovie("Guglhupfgeschwader"))
  }


}