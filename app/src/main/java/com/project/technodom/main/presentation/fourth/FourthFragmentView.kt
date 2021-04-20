package com.project.technodom.main.presentation.fourth

import com.project.technodom.entity.Movie
import com.project.technodom.entity.MovieDetails
import com.project.technodom.global.base.BaseMvpView

interface FourthFragmentView : BaseMvpView {

    fun showMovieData(movieDetails: MovieDetails)
}