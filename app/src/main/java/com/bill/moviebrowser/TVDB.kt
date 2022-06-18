package com.bill.moviebrowser

import com.bill.moviebrowser.room.Movie
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.Request

class TVDB {
  private val apiKey = "4129c71e65efdfb09e49bd4fa6b5ccd5"
  private val language = "en-US"
  private lateinit var baseUrl: String
  private lateinit var url: String

  private val gson = Gson()
  private val client = OkHttpClient()

  companion object {
    var ImageBaseUrl: String = "http://image.tmdb.org/t/p/"
    var ImageSecureBaseUrl = "https://image.tmdb.org/t/p/"
    const val ImageSize = "w92"
    private var INSTANCE: TVDB? = null

    fun getInstance(): TVDB {
      var instance = INSTANCE
      if (instance != null) return instance

      synchronized(this) {
        instance = TVDB()
        INSTANCE = instance
        return instance as TVDB
      }
    }
  }

  data class Page(
    @SerializedName("page")
    val number: Int,
    @SerializedName("results")
    val movies: List<Movie>
  )


  data class CastList(
    val id: Int,
    @SerializedName("cast")
    val list: List<Movie.Cast>
  )

  class ImageConfig(
    @SerializedName("base_url")
    val baseUrl: String,
    @SerializedName("secure_base_url")
    val secureBaseUrl: String
  )

  /**
   * Get the most recently added movie
   * Takes the apiKey and Language as parameters
   */
  suspend fun getLatestMovie(): Movie {

    baseUrl = "https://api.themoviedb.org/3/movie/latest?"
    val url = "${baseUrl}api_key=$apiKey&language=$language"
    val json = getRequest(url)
    return gson.fromJson(json, Movie::class.java)
  }

  /**
   * Get popular movies in the current page
   * Takes the apiKey, Language and the page number as parameters
   */
  suspend fun getPopularMovies(page: Int = 1): Page {
    baseUrl = "https://api.themoviedb.org/3/movie/popular?"
    val url = "${baseUrl}api_key=$apiKey&language=$language&page=$page"
    val json = getRequest(url)
    return gson.fromJson(json, Page::class.java)
  }

  suspend fun getSimilarMovies(movieId: Int, page: Int = 1): Page {
    val url =
      "https://api.themoviedb.org/3/movie/$movieId/similar?api_key=$apiKey&language=$language&page=$page"
    val json = getRequest(url)
    return gson.fromJson(json, Page::class.java)
  }

  suspend fun getCast(movieId: Int): CastList {
    val url =
      "https://api.themoviedb.org/3/movie/$movieId/credits?api_key=$apiKey&language=$language"
    val json = getRequest(url)
    return gson.fromJson(json, CastList::class.java)
  }

  suspend fun getRecommendations(movieId: Int, page: Int = 1): Page {
    val url =
      "https://api.themoviedb.org/3/movie/$movieId/recommendations?api_key=$apiKey&language=$language&page=$page"
    val json = getRequest(url)
    return gson.fromJson(json, Page::class.java)
  }

  suspend fun getTopRatedMovies(page: Int = 1): Page {
    val url =
      "https://api.themoviedb.org/3/movie/top_rated?api_key=$apiKey&language=$language&page=$page"
    val json = getRequest(url)
    return gson.fromJson(json, Page::class.java)
  }

  suspend fun getReviews(movieId: Int, page: Int = 1): Page {
    val url =
      "https://api.themoviedb.org/3/movie/$movieId/reviews?api_key=$apiKey&language=$language&page=$page"
    val json = getRequest(url)
    return gson.fromJson(json, Page::class.java)
  }

  /**
   * Get image poster configuration details
   * Takes the apiKey as a parameter
   */
  suspend fun getConfig() {
    baseUrl = "https://api.themoviedb.org/3/configuration?api_key=$apiKey"
    val json = getRequest(url)
    val imageConfig = gson.fromJson(json, ImageConfig::class.java)
    ImageBaseUrl = imageConfig.baseUrl
    ImageSecureBaseUrl = imageConfig.secureBaseUrl
  }

  private suspend fun getRequest(url: String): String {
    val request = Request.Builder().url(url).build()
    val response = client.newCall(request).execute()
    response.use { return response.body?.string() ?: "Null response" }
  }
}