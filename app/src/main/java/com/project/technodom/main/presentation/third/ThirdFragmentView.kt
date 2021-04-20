package com.project.technodom.main.presentation.third

import com.project.technodom.entity.Movie
import com.project.technodom.global.base.BaseMvpView

interface ThirdFragmentView: BaseMvpView {

    fun showMovieData(movie: Movie)
}