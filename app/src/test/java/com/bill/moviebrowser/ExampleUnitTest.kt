package com.bill.moviebrowser

import com.bill.moviebrowser.room.MovieRepository
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import javax.inject.Inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
  @Inject
  lateinit var repository: MovieRepository
  val gson = Gson()

  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  @Test
  fun movieWithCast() = runTest {
    val castList: List<CastDto> = repository.fetchMovieCast(238)
    assertEquals(1, "One")
  }
}