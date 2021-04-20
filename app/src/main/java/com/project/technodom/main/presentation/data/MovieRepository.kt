package com.project.technodom.main.presentation.data

import com.project.technodom.entity.Movie
import com.project.technodom.entity.MovieDetails
import com.project.technodom.entity.MovieResponse
import io.reactivex.Single

interface MovieRepository {

    fun getPopularMovie(page: Int): Single<MovieResponse>

    fun getTopMovie(page: Int): Single<MovieResponse>

    fun getSingleMovie(): Single<Movie>

    fun getMovieDetails(id :Int?): Single<MovieDetails>
}