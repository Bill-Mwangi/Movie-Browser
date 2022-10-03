package com.bill.moviebrowser

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bill.moviebrowser.room.MovieDao
import com.bill.moviebrowser.room.MovieDatabase

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
  private val movieDao = Room.databaseBuilder(
    InstrumentationRegistry.getInstrumentation().targetContext,
    MovieDatabase::class.java,
    "movie_database"
  ).build().movieDao()

  @Test
  fun useAppContext() {
    // Context of the app under test.
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    assertEquals("com.bill.moviebrowser", appContext.packageName)
  }

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
    assertEquals(movie, movieDao.searchMovie("Guglhupfgeschwader").get(0))
  }
}