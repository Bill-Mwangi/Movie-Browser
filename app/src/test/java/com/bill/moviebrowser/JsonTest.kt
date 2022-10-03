package com.bill.moviebrowser

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class JsonTest {
  data class TestModel(val id: Int, val description: String)

  private val gson = Gson()
  var jsonString: String = gson.toJson(TestModel(1, "Test"))

  @Test
  fun test() {
    Assert.assertEquals(jsonString, """{"id":1,"description":"Test"}""")
  }

}