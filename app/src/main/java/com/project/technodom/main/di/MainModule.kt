package com.project.technodom.main.di

import com.project.technodom.entity.MovieQuery
import com.project.technodom.main.presentation.activity.MainActivityPresenter
import com.project.technodom.main.presentation.first.FirstFragmentPresenter
import com.project.technodom.main.presentation.fourth.FourthFragmentPresenter
import com.project.technodom.main.presentation.second.SecondFragmentPresenter
import com.project.technodom.main.presentation.third.ThirdFragmentPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {

    scope(named(MainScope.MAIN_ACTIVITY_SCOPE)) {
        scoped { MainActivityPresenter() }
    }

    scope(named(MainScope.FIRST_FRAGMENT_SCOPE)) {
        scoped {(movieQuery: MovieQuery)-> FirstFragmentPresenter(movieQuery, get()) }
    }

    scope(named(MainScope.SECOND_FRAGMENT_SCOPE)) {
        scoped {(movieQuery: MovieQuery)-> SecondFragmentPresenter(movieQuery, get()) }
    }

    scope(named(MainScope.THIRD_FRAGMENT_SCOPE)) {
        scoped {ThirdFragmentPresenter(get()) }
    }

    scope(named(MainScope.FOURTH_FRAGMENT_SCOPE)) {
        scoped {(id: Int)->FourthFragmentPresenter(id, get()) }
    }
}

object MainScope {

    const val MAIN_ACTIVITY_SCOPE = "MainActivityScope"

    const val FIRST_FRAGMENT_SCOPE = "FirstFragmentScope"

    const val SECOND_FRAGMENT_SCOPE = "SecondFragmentScope"

    const val THIRD_FRAGMENT_SCOPE = "ThirdFragmentScope"

    const val FOURTH_FRAGMENT_SCOPE = "ThirdFragmentScope"
}