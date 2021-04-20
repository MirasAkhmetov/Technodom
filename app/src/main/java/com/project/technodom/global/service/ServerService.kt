package com.project.technodom.global.service

import com.project.technodom.entity.Movie
import com.project.technodom.entity.MovieDetails
import com.project.technodom.entity.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerService {

    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int?): Single<MovieDetails>

    @GET("movie/top_rated")
    fun getTopMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie/299534")
    fun getSingleMovie(): Single<Movie>
}
