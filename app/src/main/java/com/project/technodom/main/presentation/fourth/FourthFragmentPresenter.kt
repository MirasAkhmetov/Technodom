package com.project.technodom.main.presentation.fourth

import com.arellomobile.mvp.InjectViewState
import com.project.technodom.global.presentation.BasePresenter
import com.project.technodom.main.presentation.data.MovieInteractor


@InjectViewState
class FourthFragmentPresenter(
    private val id : Int,
    private val movieInteractor: MovieInteractor
): BasePresenter<FourthFragmentView>(){




    fun onFirstInit() {
        getMovieDetails()
    }


    private fun getMovieDetails() {
        movieInteractor.getMovieDetails(id)
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