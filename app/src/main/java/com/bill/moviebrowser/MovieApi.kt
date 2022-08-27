package com.bill.moviebrowser

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

  /**
   * Get image poster configuration details
   * Takes the apiKey as a parameter
   */
  @GET("3/configuration?")
  suspend fun getConfig(@Query("api_key") apiKey: String): ConfigDto

  /**
   * Get the most recently added movie
   * Takes the apiKey and Language as parameters
   */
  @GET("3/movie/latest?")
  suspend fun getLatestMovie(
    @Query("api_key") apiKey: String,
    @Query("language") language: String
  ): MovieDto

  /**
   * Get popular movies in the current page
   * Takes the apiKey, Language and the page number as parameters
   */
  @GET("3/movie/popular?")
  suspend fun getPopularMovies(
    @Query("api_key") apiKey: String,
    @Query("language") language: String,
    @Query("page") page: Int = 1
  ): PageDto

  @GET("3/movie/{movieId}/similar?")
  suspend fun getSimilarMovies(
    @Path("movieId") movieId: Int,
    @Query("api_key") apiKey: String,
    @Query("language") language: String,
    @Query("page") page: Int = 1
  ): PageDto

  @GET("3/movie/{movieId}/credits?")
  suspend fun getCast(
    @Path("movieId") movieId: Int,
    @Query("api_key") apiKey: String,
    @Query("language") language: String
  ): CastDto

  @GET("3/movie/{movieId}/recommendations?")
  suspend fun getRecommendations(
    @Path("movieId") movieId: Int,
    @Query("api_key") apiKey: String,
    @Query("language") language: String,
    @Query("page") page: Int = 1
  ): PageDto

  @GET("3/movie/top_rated?")
  suspend fun getTopRatedMovies(
    @Query("api_key") apiKey: String,
    @Query("language") language: String,
    @Query("page") page: Int = 1
  ): PageDto

  @GET("3/movie/{movieId}/reviews?")
  suspend fun getReviews(
    @Path("movieId") movieId: Int,
    @Query("api_key") apiKey: String,
    @Query("language") language: String,
    @Query("page") page: Int = 1
  ): PageDto
}