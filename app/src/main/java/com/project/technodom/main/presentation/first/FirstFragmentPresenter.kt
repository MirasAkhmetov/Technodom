package com.project.technodom.main.presentation.first

import com.arellomobile.mvp.InjectViewState
import com.project.technodom.entity.Movie
import com.project.technodom.entity.MovieQuery
import com.project.technodom.global.di.getErrorMessage
import com.project.technodom.global.presentation.BasePresenter
import com.project.technodom.global.presentation.Paginator
import com.project.technodom.main.presentation.data.MovieInteractor

@InjectViewState
class FirstFragmentPresenter(
    private val movieQuery: MovieQuery,
    private val movieInteractor: MovieInteractor
) : BasePresenter<FirstFragmentView>() {

    fun onFirstInit() {

        paginator.refresh()
        paginator2.refresh()
    }


    fun loadDataNextPage() = paginator.loadNewPage()

    fun loadDataNextPage2() = paginator2.loadNewPage()

    private val paginator = Paginator(
        {
            movieQuery.page = it
            movieInteractor.getPopularMovie(movieQuery)
        },
        object : Paginator.ViewController<Movie> {
            override fun showEmptyProgress(show: Boolean) {

            }

            override fun showEmptyError(show: Boolean, error: Throwable?) {

            }

            override fun showEmptyView(show: Boolean) {
                viewState?.showMovieData(listOf())
            }

            override fun showData(show: Boolean, data: List<Movie>) {
                viewState?.showMovieData(data)
            }

            override fun showErrorMessage(error: Throwable) {
                viewState?.showMessage(error.getErrorMessage())
            }

            override fun showRefreshProgress(show: Boolean) {

            }

            override fun showPageProgress(show: Boolean) {

            }
        })

    private val paginator2 = Paginator(
        {
            movieQuery.page = it
            movieInteractor.getTopMovie(movieQuery)
        },
        object : Paginator.ViewController<Movie> {
            override fun showEmptyProgress(show: Boolean) {

            }

            override fun showEmptyError(show: Boolean, error: Throwable?) {

            }

            override fun showEmptyView(show: Boolean) {
                viewState?.showTopMovieData(listOf())
            }

            override fun showData(show: Boolean, data: List<Movie>) {
                viewState?.showTopMovieData(data)
            }

            override fun showErrorMessage(error: Throwable) {
                viewState?.showMessage(error.getErrorMessage())
            }

            override fun showRefreshProgress(show: Boolean) {

            }

            override fun showPageProgress(show: Boolean) {

            }
        })

    fun onOrderItemSelected(movie: Movie){
        movie.id?.let { viewState.openMovieDetail(it) }
    }
}