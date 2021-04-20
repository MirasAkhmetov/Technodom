package com.project.technodom.main.presentation.first

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.project.technodom.R
import com.project.technodom.entity.Movie
import com.project.technodom.entity.MovieQuery
import com.project.technodom.global.base.BaseFragment
import com.project.technodom.global.di.addFragmentWithBackStack
import com.project.technodom.main.di.MainScope
import com.project.technodom.main.presentation.adapter.FirstFragmentAdapter
import com.project.technodom.main.presentation.adapter.FirstFragmentAdapterTwo
import com.project.technodom.main.presentation.fourth.FourthFragment
import kotlinx.android.synthetic.main.fragment_first.*
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class FirstFragment : BaseFragment(), FirstFragmentView {

    companion object {
        val TAG = "FirstFragment"

        fun newInstance(): FirstFragment =
            FirstFragment()
    }

    @InjectPresenter
    lateinit var presenter: FirstFragmentPresenter


    @ProvidePresenter
    fun providePresenter(): FirstFragmentPresenter {
        getKoin().getScopeOrNull(MainScope.FIRST_FRAGMENT_SCOPE)?.close()
        val scope = getKoin().createScope(
            MainScope.FIRST_FRAGMENT_SCOPE,
            named(MainScope.FIRST_FRAGMENT_SCOPE)
        )
        return scope.get { parametersOf(MovieQuery(page = 1)) }
    }

    override val layoutRes: Int
        get() = R.layout.fragment_first

    private val adapter = FirstFragmentAdapter(
        { presenter.loadDataNextPage() },
        { presenter.onOrderItemSelected(it) })
    private val adapterTwo = FirstFragmentAdapterTwo({ presenter.loadDataNextPage2() })

    override fun setUp(savedInstanceState: Bundle?) {
        presenter.onFirstInit()
        recyclerVertical.adapter = adapter
        recyclerHorizontal.adapter = adapterTwo


        val gridLayoutManager = GridLayoutManager(context, 2)
        recyclerVertical.layoutManager = gridLayoutManager
        recyclerVertical.setHasFixedSize(true)

    }

    override fun showMovieData(dataList: List<Movie>) {
        adapter.submitData(dataList)

    }

    override fun showTopMovieData(dataList: List<Movie>) {
        adapterTwo.submitData(dataList)
    }

    override fun openMovieDetail(id: Int) {
          activity?.addFragmentWithBackStack (
            R.id.container,
            FourthFragment.newInstance(id),
            FourthFragment.TAG
        )
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        getKoin().getScopeOrNull(MainScope.FIRST_FRAGMENT_SCOPE)?.close()
    }
}