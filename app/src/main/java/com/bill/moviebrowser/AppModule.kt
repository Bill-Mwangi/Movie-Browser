package com.bill.moviebrowser

import android.content.Context
import androidx.room.Room
import com.bill.moviebrowser.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun providesMovieApi(): MovieApi {
    return Retrofit.Builder()
      .baseUrl("https://api.themoviedb.org/")
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
      .create()
  }

  @Provides
  @Singleton
  fun providesRoomDatabase(@ApplicationContext context: Context): MovieDatabase {
    return Room.databaseBuilder(
      context.applicationContext,
      MovieDatabase::class.java,
      "movie_database"
    ).build()
  }

  @Provides
  @Singleton
  @Named("apikey")
  fun providesApiKey() = "4129c71e65efdfb09e49bd4fa6b5ccd5"

  @Provides
  @Singleton
  @Named("language")
  fun provideslanguage() = "en-US"

  @Provides
  @Singleton
  @Named("imageSize")
  fun ImageSize() = "w92"
}