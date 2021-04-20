package com.project.technodom.main.presentation.second

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.project.technodom.R
import com.project.technodom.entity.Movie
import com.project.technodom.entity.MovieQuery
import com.project.technodom.global.base.BaseFragment
import com.project.technodom.main.di.MainScope
import com.project.technodom.main.presentation.adapter.SecondFragmentAdapter
import kotlinx.android.synthetic.main.fragment_second.*
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class SecondFragment : BaseFragment(), SecondFragmentView {


    @InjectPresenter
    lateinit var presenter: SecondFragmentPresenter

    @ProvidePresenter
    fun providePresenter(): SecondFragmentPresenter {
        getKoin().getScopeOrNull(MainScope.SECOND_FRAGMENT_SCOPE)?.close()
        val scope = getKoin().createScope(
            MainScope.SECOND_FRAGMENT_SCOPE,
            named(MainScope.SECOND_FRAGMENT_SCOPE)
        )
        return scope.get { parametersOf(MovieQuery(page = 1)) }
    }

    private val adapter = SecondFragmentAdapter({ presenter.loadDataNextPage() })

    override val layoutRes: Int
        get() = R.layout.fragment_second

    override fun setUp(savedInstanceState: Bundle?) {
        presenter.onFirstInit()
        recyclerSecond.adapter = adapter
    }

    override fun showMovieData(dataList: List<Movie>) {
        adapter.submitData(dataList)
    }
}