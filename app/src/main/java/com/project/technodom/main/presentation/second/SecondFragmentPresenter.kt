package com.project.technodom.main.presentation.second

import com.arellomobile.mvp.InjectViewState
import com.project.technodom.entity.Movie
import com.project.technodom.entity.MovieQuery
import com.project.technodom.global.di.getErrorMessage
import com.project.technodom.global.presentation.BasePresenter
import com.project.technodom.global.presentation.Paginator
import com.project.technodom.main.presentation.data.MovieInteractor

@InjectViewState
class SecondFragmentPresenter(
    private val movieQuery: MovieQuery,
    private val movieInteractor: MovieInteractor
) : BasePresenter<SecondFragmentView>() {

    fun onFirstInit() {

        paginator.refresh()

    }

    fun loadDataNextPage() = paginator.loadNewPage()

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
}