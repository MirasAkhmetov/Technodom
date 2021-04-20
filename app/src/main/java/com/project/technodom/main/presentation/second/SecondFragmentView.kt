package com.project.technodom.main.presentation.second

import com.project.technodom.entity.Movie
import com.project.technodom.global.base.BaseMvpView

interface SecondFragmentView : BaseMvpView {

    fun showMovieData(dataList : List<Movie>)
}