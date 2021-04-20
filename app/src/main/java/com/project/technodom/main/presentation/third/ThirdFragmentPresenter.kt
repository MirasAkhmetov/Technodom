package com.project.technodom.main.presentation.third

import com.arellomobile.mvp.InjectViewState
import com.project.technodom.global.presentation.BasePresenter
import com.project.technodom.main.presentation.data.MovieInteractor

@InjectViewState
class ThirdFragmentPresenter(
    private val movieInteractor: MovieInteractor
) : BasePresenter<ThirdFragmentView>() {

    fun onFirstInit() {
        getSingleMovie()
    }


    private fun getSingleMovie() {
        movieInteractor.getSingleMovie()
            .subscribe(
                {
                    viewState.showMovieData(it)
                },
                {
                    it.printStackTrace()
                }
            ).connect()
    }
}