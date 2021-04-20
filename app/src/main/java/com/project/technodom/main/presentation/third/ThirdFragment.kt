package com.project.technodom.main.presentation.third

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.project.technodom.R
import com.project.technodom.entity.Movie
import com.project.technodom.global.base.BaseFragment
import com.project.technodom.global.di.ServiceProperties
import com.project.technodom.main.di.MainScope
import kotlinx.android.synthetic.main.fragment_third.*
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class ThirdFragment: BaseFragment(), ThirdFragmentView {


    @InjectPresenter
    lateinit var presenter: ThirdFragmentPresenter

    @ProvidePresenter
    fun providePresenter(): ThirdFragmentPresenter {
        getKoin().getScopeOrNull(MainScope.THIRD_FRAGMENT_SCOPE)?.close()
         return getKoin().createScope(
            MainScope.THIRD_FRAGMENT_SCOPE,
            named(MainScope.THIRD_FRAGMENT_SCOPE)
        ).get()
    }


    override val layoutRes: Int
        get() = R.layout.fragment_third

    override fun setUp(savedInstanceState: Bundle?) {
        presenter.onFirstInit()

    }

    override fun showMovieData(movie: Movie) {

        val moviePosterURL = ServiceProperties.POSTER_BASE_URL + movie?.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .optionalCircleCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imgCircle)

        txtTitle.text = movie.title
        txtOverview.text = movie.overview
    }

}