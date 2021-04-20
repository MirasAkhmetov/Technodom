package com.project.technodom.global.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager



internal fun FragmentManager.replaceFragment(containerViewId: Int,
                                             fragment: Fragment,
                                             tag: String) {

    for (i in 0..this.backStackEntryCount) this.popBackStack()

    this.beginTransaction()
        .disallowAddToBackStack()
        .replace(containerViewId, fragment, tag)
        .commitAllowingStateLoss()
}


internal fun FragmentActivity.addFragmentWithBackStack(containerViewId: Int,
                                                       fragment: Fragment,
                                                       tag: String) {
    this.supportFragmentManager
        .beginTransaction()
        .add(containerViewId, fragment, tag)
        .addToBackStack(tag)
        .commit()
}
