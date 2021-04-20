package com.project.technodom.main.presentation.data

import com.project.technodom.entity.Movie
import com.project.technodom.entity.MovieDetails
import com.project.technodom.entity.MovieQuery
import com.project.technodom.global.system.SchedulersProvider
import io.reactivex.Single

class MovieInteractor(
    private val movieRepository: MovieRepository,
    private val schedulersProvider: SchedulersProvider
) {

    fun getPopularMovie(movieQuery: MovieQuery): Single<List<Movie>?> =
        movieRepository.getPopularMovie(
            page = movieQuery.page
        )
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui()).map { it.movieList }

    fun getTopMovie(movieQuery: MovieQuery): Single<List<Movie>?> =
        movieRepository.getTopMovie(
            page = movieQuery.page
        )
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui()).map { it.movieList }

    fun getSingleMovie(): Single<Movie> =
        movieRepository.getSingleMovie()
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())

    fun getMovieDetails(id: Int?): Single<MovieDetails> =
        movieRepository.getMovieDetails(id)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
}