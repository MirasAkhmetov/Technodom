package com.project.technodom.main.presentation.activity

import com.arellomobile.mvp.InjectViewState
import com.project.technodom.global.presentation.BasePresenter

@InjectViewState
class MainActivityPresenter : BasePresenter<MainActivityView>() {

    fun onFirstInit() {

        viewState?.openFirstFragment()

    }

}