package com.project.technodom.main.presentation.first

import com.project.technodom.entity.Movie
import com.project.technodom.global.base.BaseMvpView

interface FirstFragmentView : BaseMvpView {

    fun showMovieData(dataList : List<Movie>)

    fun showTopMovieData(dataList : List<Movie>)

    fun openMovieDetail(id: Int)
}