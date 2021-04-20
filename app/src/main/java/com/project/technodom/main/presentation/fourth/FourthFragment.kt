package com.project.technodom.main.presentation.fourth

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.project.technodom.R
import com.project.technodom.entity.MovieDetails
import com.project.technodom.global.base.BaseFragment
import com.project.technodom.global.di.ServiceProperties.POSTER_BASE_URL
import com.project.technodom.main.di.MainScope
import kotlinx.android.synthetic.main.fragment_fourth.*
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import java.text.NumberFormat
import java.util.*

class FourthFragment : BaseFragment(), FourthFragmentView {

    companion object {
        val TAG = "FourthFragment"
        val BUNDLE_ID = "id"

        fun newInstance(id: Int): FourthFragment =
            FourthFragment().apply {
                arguments = Bundle().apply {
                    putInt(BUNDLE_ID, id)
                }
            }
    }

    @InjectPresenter
    lateinit var presenter: FourthFragmentPresenter


    @ProvidePresenter
    fun providePresenter(): FourthFragmentPresenter {
        getKoin().getScopeOrNull(MainScope.FOURTH_FRAGMENT_SCOPE)?.close()
        val scope =  getKoin().createScope(
            MainScope.FOURTH_FRAGMENT_SCOPE,
            named(MainScope.FOURTH_FRAGMENT_SCOPE)
        )
        val id = arguments?.getInt(BUNDLE_ID)
        return scope.get{parametersOf(id)}

    }

    override val layoutRes: Int
        get() = R.layout.fragment_fourth

    override fun setUp(savedInstanceState: Bundle?) {
        presenter.onFirstInit()
    }

    override fun showMovieData(movieDetails: MovieDetails) {
        movie_title.text = movieDetails.title
        movie_tagline.text = movieDetails.tagline
        movie_release_date.text = movieDetails.releaseDate
        movie_rating.text = movieDetails.rating.toString()
        movie_runtime.text = movieDetails.runtime.toString() + " minutes"
        movie_overview.text = movieDetails.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        movie_budget.text = formatCurrency.format(movieDetails.budget)
        movie_revenue.text = formatCurrency.format(movieDetails.revenue)

        val moviePosterURL = POSTER_BASE_URL + movieDetails.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(iv_movie_poster);

    }
}