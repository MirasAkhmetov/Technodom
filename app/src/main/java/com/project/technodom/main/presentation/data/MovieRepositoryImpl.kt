package com.project.technodom.main.presentation.data

import com.project.technodom.entity.Movie
import com.project.technodom.entity.MovieDetails
import com.project.technodom.entity.MovieResponse
import com.project.technodom.global.service.ServerService
import io.reactivex.Single

class MovieRepositoryImpl(
    private val serverService: ServerService
) : MovieRepository {

    override fun getPopularMovie(page: Int): Single<MovieResponse> =
        serverService.getPopularMovie(page)

    override fun getTopMovie(page: Int): Single<MovieResponse> =
        serverService.getTopMovie(page)

    override fun getMovieDetails(id: Int?): Single<MovieDetails> =
        serverService.getMovieDetails(id)

    override fun getSingleMovie(): Single<Movie> =
        serverService.getSingleMovie()

}